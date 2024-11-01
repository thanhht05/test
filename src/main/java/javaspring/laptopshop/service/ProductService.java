package javaspring.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}
