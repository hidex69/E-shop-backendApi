package com.eshop.demo.repository;

import com.eshop.demo.models.product.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {

}
