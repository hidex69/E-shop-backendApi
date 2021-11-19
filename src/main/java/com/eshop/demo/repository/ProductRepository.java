package com.eshop.demo.repository;

import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByCategoryEntity(CategoryEntity categoryEntity);
}
