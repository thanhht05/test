package javaspring.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javaspring.laptopshop.domain.Order;
import javaspring.laptopshop.domain.OrderDetail;
import javaspring.laptopshop.repository.OrderDetailRepository;
import javaspring.laptopshop.repository.OrderRepository;
import javaspring.laptopshop.service.OrderService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderService orderService;

    public OrderController(OrderDetailRepository orderDetailRepository,
            OrderService orderService) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getOrderPage(Model model, @RequestParam("page") Optional<String> pageOptional) {

        int currentPage = 1;
        try {
            if (pageOptional.isPresent()) {
                currentPage = Integer.parseInt(pageOptional.get());
            }
        } catch (Exception e) {

        }
        Pageable pageable = PageRequest.of(currentPage - 1, 5);
        // Page<Order> orders = this.orderRepository.findAll(pageable);
        Page<Order> orders = this.orderService.getAll(pageable);

        List<Order> listOrder = orders.getContent();
        int totalPage = orders.getTotalPages();

        model.addAttribute("order", listOrder);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getMethodName(Model model, @PathVariable long id) {
        // Optional<Order> order = this.orderRepository.findById(id);
        Order order = this.orderService.findById(id);

        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrder(order);

        model.addAttribute("orderDetails", orderDetails);

        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(Model model, @PathVariable long id) {
        Order order = this.orderService.findById(id);

        model.addAttribute("order", order);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postMethodName(Model model, @ModelAttribute("order") Order order) {
        Order currentOrder = this.orderService.findById(order.getId());
        if (currentOrder != null) {

            currentOrder.setStatus(order.getStatus());
            this.orderService.saveOrder(currentOrder);
        }

        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("order", new Order());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String handleDeleteOrder(@ModelAttribute("order") Order order) {
        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrder(order);
        long id = order.getId();
        this.orderService.handleDeleteOrder(orderDetails, id);
        return "redirect:/admin/order";
    }

}
