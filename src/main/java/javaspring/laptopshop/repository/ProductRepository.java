package javaspring.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import javaspring.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    Product save(Product product);

    List<Product> findAll();

    void deleteById(long id);

    Page<Product> findAll(Pageable pageable);

}