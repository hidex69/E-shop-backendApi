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

    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingTotalAsc(String name);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingTotalDesc(String name);

    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostAsc(String name,
                                                                                        CategoryEntity categoryEntity);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostDesc(String name,
                                                                                        CategoryEntity categoryEntity);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingTotalAsc(String name,
                                                                                                CategoryEntity categoryEntity);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingTotalDesc(String name,
                                                                                                CategoryEntity categoryEntity);

    List<Product> findProductsByNameContainingIgnoreCaseOrderByCostAsc(String name, Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByCostDesc(String name, Pageable pageable);

    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingTotalAsc(String name, Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseOrderByRatingTotalDesc(String name, Pageable pageable);

    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostAsc(String name,
                                                                                        CategoryEntity categoryEntity,
                                                                                        Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostDesc(String name,
                                                                                         CategoryEntity categoryEntity,
                                                                                         Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingTotalAsc(String name,
                                                                                               CategoryEntity categoryEntity,
                                                                                               Pageable pageable);
    List<Product> findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingTotalDesc(String name,
                                                                                                CategoryEntity categoryEntity,
                                                                                                Pageable pageable);


}
