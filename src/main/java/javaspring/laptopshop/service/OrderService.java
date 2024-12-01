package javaspring.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javaspring.laptopshop.domain.OrderDetail;
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
}
