package javaspring.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaspring.laptopshop.domain.Order;
import javaspring.laptopshop.domain.Product;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
