package javaspring.laptopshop.controller.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javaspring.laptopshop.domain.Order;
import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.domain.User;
import javaspring.laptopshop.domain.dto.RegisterDTO;
import javaspring.laptopshop.service.OrderService;
import javaspring.laptopshop.service.ProductService;
import javaspring.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomepageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;

    HomepageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder,
            OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getMethodName(Model model, @RequestParam("page") Optional<String> pageOptional) {
        int currentPage = 1;
        try {
            if (pageOptional.isPresent()) {
                currentPage = Integer.parseInt(pageOptional.get());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(currentPage - 1, 10);
        Page<Product> prs = this.productService.getAllProduct(pageable);
        List<Product> listProducts = prs.getContent();
        int totalPage = prs.getTotalPages();

        model.addAttribute("products", listProducts);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult registerBindingResult) {
        if (registerBindingResult.hasErrors()) {
            System.out.println("CO LOI");
            return "client/auth/register";
        }
        User user = this.userService.registerDTOtoUser(registerDTO);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/deny";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        User user = new User();
        long id = (long) session.getAttribute("id");

        user.setId(id);

        List<Order> orders = this.orderService.handleFindOrderByUser(user);

        model.addAttribute("orders", orders);
        return "client/cart/order-history";
    }

}
