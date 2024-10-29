package javaspring.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaspring.laptopshop.domain.Product;
import javaspring.laptopshop.domain.Role;
import javaspring.laptopshop.domain.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);


}