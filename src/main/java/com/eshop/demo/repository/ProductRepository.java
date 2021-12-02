package com.eshop.demo.repository;

import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.models.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByCategoryEntityAndNameContainingIgnoreCase(CategoryEntity categoryEntity, String query);
    List<Product> findProductsByCategoryEntityAndNameContainingIgnoreCase(CategoryEntity categoryEntity,
                                                                          Pageable pageable, String query);

    List<Product> findProductsByNameContainingIgnoreCase(String name);
    List<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Product> findProductsByNameContainingIgnoreCaseOrderByCostAsc(String name);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByCostDesc(String name);

    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingCounterAsc(String name);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingCounterDesc(String name);

    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostAsc(String name,
                                                                                        CategoryEntity categoryEntity);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostDesc(String name,
                                                                                        CategoryEntity categoryEntity);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterAsc(String name,
                                                                                                CategoryEntity categoryEntity);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterDesc(String name,
                                                                                                CategoryEntity categoryEntity);

    List<Product> findProductsByNameContainingIgnoreCaseOrderByCostAsc(String name, Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByCostDesc(String name, Pageable pageable);

    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingCounterAsc(String name, Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingCounterDesc(String name, Pageable pageable);

    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostAsc(String name,
                                                                                        CategoryEntity categoryEntity,
                                                                                        Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostDesc(String name,
                                                                                         CategoryEntity categoryEntity,
                                                                                         Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterAsc(String name,
                                                                                               CategoryEntity categoryEntity,
                                                                                               Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterDesc(String name,
                                                                                                CategoryEntity categoryEntity,
                                                                                                Pageable pageable);


}
