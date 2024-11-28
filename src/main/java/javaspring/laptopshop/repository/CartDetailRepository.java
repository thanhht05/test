package javaspring.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaspring.laptopshop.domain.Cart;
import javaspring.laptopshop.domain.CartDetail;
import javaspring.laptopshop.domain.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    CartDetail findByCartAndProduct(Cart cart, Product product);

    CartDetail findByid(Long id);

    void deleteById(long id);
}
