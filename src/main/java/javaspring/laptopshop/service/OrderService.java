package javaspring.laptopshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javaspring.laptopshop.domain.Order;
import javaspring.laptopshop.domain.OrderDetail;
import javaspring.laptopshop.domain.User;
import javaspring.laptopshop.repository.OrderDetailRepository;
import javaspring.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void handleDeleteOrder(List<OrderDetail> orderDetails, long id) {
        for (OrderDetail oDetail : orderDetails) {
            this.orderDetailRepository.delete(oDetail);
        }
        this.orderRepository.deleteById(id);
    }

    public List<Order> handleFindOrderByUser(User user) {
        return this.orderRepository.getAllOrderByUser(user);
    }

    public Page<Order> getAll(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Order findById(long id) {
        return this.orderRepository.getOrderById(id);
    }

    public Order saveOrder(Order order) {
        Order order2 = this.orderRepository.save(order);
        return order2;
    }

}
