package javaspring.laptopshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaspring.laptopshop.domain.Order;
import javaspring.laptopshop.domain.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllOrderByUser(User user);

    Order getOrderById(long id);

    Order save(Order order);

    Page<Order> findAll(Pageable pageable);
}
