package com.eshop.demo.DAO;

import com.eshop.demo.exceptions.NoSuchCategoryException;
import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.models.product.Product;
import com.eshop.demo.models.product.ProductDto;
import com.eshop.demo.repository.CategoryEntityRepository;
import com.eshop.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;

    public List<Product> loadProducts(int page, int count, String category, String query,
                                      String sort, String order) throws NoSuchCategoryException {

        List<Product> products = new ArrayList<>();
        count = Math.max(count, 0);
        page = Math.max(page, 0);

        if (count == 0 && page == 0) {
            if (category.equals("")) {
                if (sort.equals("")) {
                    products = productRepository.findProductsByNameContainingIgnoreCase(query);
                } else {
                    products = loadWithSort(query, sort, order);
                }
            }
            else {
                if (sort.equals("")) {
                    products = loadByCategory(category, query);
                } else {
                    products = loadWithSortAndCategory(query, sort, order, category);
                }
            }
        } else {
            if (category.equals("")) {
                if (sort.equals("")) {
                    products = productRepository.findProductsByNameContainingIgnoreCase(query, PageRequest.of(page, count));
                } else {
                    products = loadWithSortAndPageable(query, sort, order, page, count);
                }
            }
            else {
                if ((sort.equals(""))) {
                    products = loadByCategoryAndPage(category, page, count, query);
                } else {
                    products = loadByCategoryAndPageWithSort(query, page, count, sort, order, category);
                }
            }
        }

        return products;
    }


    public Product loadProduct(int id) {

        return productRepository.findById(id).get();
    }

    public void deleteProduct(int id) {
        String sql = "delete from product where id = ?";

        jdbcTemplate.update(sql, id);
    }

    public void addProduct(ProductDto productDto) {
        String sql = "insert into product (name, shortdescription, cost, rating_total, rating_counter)" +
                " values(?, ?, ?, 0, 0)";

        jdbcTemplate.update(sql, productDto.getName(), productDto.getShortDescription(), productDto.getCost());
    }

    public void updateProduct(ProductDto productDto, int id) {
        String sql = "update product set name = ?, shortdescription = ?, cost = ? where id = ?";

        jdbcTemplate.update(sql, productDto.getName(), productDto.getShortDescription(),
                productDto.getCost(), id);
    }

    public void loadToBasket(int user_id, int product_id) {
        String sql = "insert into basket(user_id, product_id) values(?, ?)";

        jdbcTemplate.update(sql, user_id, product_id);
    }

    public void deleteFromBasket(int user_id, int product_id) {
        String sql = "delete from basket where user_id = ? and product_id = ?";

        jdbcTemplate.update(sql, user_id, product_id);
    }

    public void rateProduct(int product_id, int rate) {
        String sql = "update product set rating_total = 1, rating_counter = 2 " +
                "where id = ?";

        jdbcTemplate.update(sql, product_id);
    }

    private List<Product> loadByCategory(String categoryName, String query) throws NoSuchCategoryException {
        CategoryEntity categoryEntity = categoryEntityRepository.findByName(categoryName.toUpperCase());
        if (categoryEntity == null) {
            throw  new NoSuchCategoryException("Bad request(No such category)");
        }

        return productRepository.findProductsByCategoryEntityAndNameContainingIgnoreCase(categoryEntity, query);
    }

    private List<Product> loadWithSort(String query, String sort, String order) {
        List<Product> products = new ArrayList<>();
        switch (sort.toLowerCase()) {
            case "cost":
                products = order.toLowerCase().equals("desc") ?
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByCostDesc(query) :
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByCostAsc(query);
                break;
            case "rating":
                products = order.toLowerCase().equals("desc") ?
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByRatingCounterDesc(query) :
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByRatingCounterAsc(query);
                break;
        }
        return products;
    }


    private List<Product> loadWithSortAndPageable(String query, String sort, String order, int page, int count) {
        List<Product> products = new ArrayList<>();
        switch (sort.toLowerCase()) {
            case "cost":
                products = order.toLowerCase().equals("desc") ?
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByCostDesc(query, PageRequest.of(page, count)) :
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByCostAsc(query, PageRequest.of(page, count));
                break;
            case "rating":
                products = order.toLowerCase().equals("desc") ?
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByRatingCounterDesc(query, PageRequest.of(page, count)) :
                        productRepository.findProductsByNameContainingIgnoreCaseOrderByRatingCounterAsc(query, PageRequest.of(page, count));
                break;
        }
        return products;
    }

    private List<Product> loadWithSortAndCategory(String query, String sort, String order, String category) {
        List<Product> products = new ArrayList<>();
        CategoryEntity categoryEntity = categoryEntityRepository.findByName(category.toUpperCase());
        switch (sort.toLowerCase()) {
            case "cost":
                products = order.toLowerCase().equals("desc") ?
                        productRepository
                                .findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostDesc(query, categoryEntity) :
                        productRepository.findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostAsc(query, categoryEntity);
                break;
            case "rating":
                products = order.toLowerCase().equals("desc") ?
                        productRepository.findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterDesc(query, categoryEntity) :
                        productRepository.findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterAsc(query, categoryEntity);
                break;
        }
        return products;
    }

    private List<Product> loadByCategoryAndPage(String categoryName, int page, int count, String query) throws NoSuchCategoryException {
        CategoryEntity categoryEntity = categoryEntityRepository.findByName(categoryName.toUpperCase());
        if (categoryEntity == null) {
            throw new NoSuchCategoryException("Bad request(No such category)");
        }

        return productRepository.findProductsByCategoryEntityAndNameContainingIgnoreCase(categoryEntity,
                PageRequest.of(page, count), query);
    }

    private List<Product> loadByCategoryAndPageWithSort(String query, int page, int count, String sort, String order, String category) {
        List<Product> products = new ArrayList<>();
        CategoryEntity categoryEntity = categoryEntityRepository.findByName(category.toUpperCase());
        switch (sort.toLowerCase()) {
            case "cost":
                products = order.toLowerCase().equals("desc") ?
                        productRepository
                                .findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostDesc(query, categoryEntity, PageRequest.of(page, count)) :
                        productRepository.findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByCostAsc(query, categoryEntity, PageRequest.of(page, count));
                break;
            case "rating":
                products = order.toLowerCase().equals("desc") ?
                        productRepository.findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterDesc(query, categoryEntity, PageRequest.of(page, count)) :
                        productRepository.findProductsByNameContainingIgnoreCaseAndCategoryEntityOrderByRatingCounterAsc(query, categoryEntity, PageRequest.of(page, count));
                break;
        }
        return products;
    }
}
