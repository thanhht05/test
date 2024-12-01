package javaspring.laptopshop.service;

import static org.mockito.Mockito.lenient;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import javaspring.laptopshop.domain.Cart;
import javaspring.laptopshop.domain.CartDetail;
import javaspring.laptopshop.domain.Order;
import javaspring.laptopshop.domain.OrderDetail;
import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.domain.User;
import javaspring.laptopshop.repository.CartDetailRepository;
import javaspring.laptopshop.repository.CartRepository;
import javaspring.laptopshop.repository.OrderDetailRepository;
import javaspring.laptopshop.repository.OrderRepository;
import javaspring.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Product handleSaveProduct(Product product) {
        Product productItem = this.productRepository.save(product);
        return productItem;
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProductBId(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddAProductToCart(String email, Long productId, HttpSession session) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            // check user co Cart hay chua neu chua co thi tao moi

            if (cart == null) {
                // tao moi cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);

            }
            // tim product by id
            Optional<Product> productOptional = this.productRepository.findById(productId);

            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();

                // check sp da duoc them vao gio hang truoc day chua
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                // neu sp chua duoc them thi tao moi sp roi them vao
                if (oldDetail == null) {

                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setPrice(realProduct.getPrice());
                    cartDetail.setQuantity(1);
                    this.cartDetailRepository.save(cartDetail);

                    // update cart sum
                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", sum);
                } else {
                    // co roi thi tang quantity len 1
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);

                }

            }

        }
    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleDeleteCartProduct(Long id, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(id);

        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart cart = cartDetail.getCart();

            this.cartDetailRepository.deleteById(id);

            if (cart.getSum() > 1) {
                int sum = cart.getSum() - 1;
                cart.setSum(sum);

                session.setAttribute("sum", sum);
                this.cartRepository.save(cart);
            } else {
                this.cartRepository.deleteById(cart.getId());
                session.setAttribute("sum", 0);
            }

        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail realDetail = cdOptional.get();
                realDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(realDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverPhone,
            String receiverAddress) {

        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null) {
                // create order
                Order order = new Order();
                order.setUser(user);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setReceiverName(receiverName);
                order.setStatus("pending");

                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice() * cd.getQuantity();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());

                    this.orderDetailRepository.save(orderDetail);
                }

                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }
                this.cartRepository.deleteById(cart.getId());
                session.setAttribute("sum", 0);

            }
        }

    }

}
