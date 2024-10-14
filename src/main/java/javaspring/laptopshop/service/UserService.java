package javaspring.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import javaspring.laptopshop.domain.User;
import javaspring.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleSaveUser(User user) {
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public User getUserById(int id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserById(int id) {
        this.userRepository.deleteById(id);
    }
    

}
