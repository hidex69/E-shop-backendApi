package com.eshop.demo.repository;

import com.eshop.demo.models.product.Product;
import com.eshop.demo.models.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Set<Product> findAllById(Integer id);
    UserEntity findByLogin(String name);
}
