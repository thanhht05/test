package javaspring.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderService orderService;

    public OrderController(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
            OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getOrderPage(Model model) {
        List<Order> orders = this.orderRepository.findAll();
        model.addAttribute("order", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getMethodName(Model model, @PathVariable long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        Order order2 = null;
        if (order.isPresent()) {
            order2 = order.get();
        }
        
        List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrder(order2);
        

        model.addAttribute("orderDetails", orderDetails);

        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(Model model, @PathVariable long id) {
        Optional<Order> order = this.orderRepository.findById(id);
        Order otherOrder = (order.isPresent()) ? order.get() : null;

        model.addAttribute("order", otherOrder);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postMethodName(Model model, @ModelAttribute("order") Order order) {
        Optional<Order> currentOrder = this.orderRepository.findById(order.getId());
        Order realOrder = (currentOrder.isPresent()) ? currentOrder.get() : null;
        if (realOrder != null) {

            realOrder.setStatus(order.getStatus());
            this.orderRepository.save(realOrder);
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
