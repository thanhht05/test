package javaspring.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.domain.Role;
import javaspring.laptopshop.domain.User;
import javaspring.laptopshop.repository.ProductRepository;
import javaspring.laptopshop.repository.RoleRepository;
import javaspring.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User handleSaveUser(User user) {
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
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
