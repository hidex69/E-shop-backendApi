package com.eshop.demo.DAO;

import com.eshop.demo.exceptions.NoSuchCategoryException;
import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.models.product.Product;
import com.eshop.demo.models.product.ProductDto;
import com.eshop.demo.repository.CategoryEntityRepository;
import com.eshop.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryEntityRepository categoryEntityRepository;


    public List<Product> loadAllProducts(int page, int count) {

        count = Math.max(count, 0);
        page = Math.max(page, 0);

        if (count == 0 && page == 0) {
            return productRepository.findAll();
        } else {
            return productRepository.findAll(PageRequest.of(page, count)).getContent();
        }
    }

    public List<Product> loadByCategory(String categoryName) throws NoSuchCategoryException {
        CategoryEntity categoryEntity = categoryEntityRepository.findByName(categoryName.toUpperCase());
        if (categoryEntity == null) {
            throw  new NoSuchCategoryException("Bad request(No such category)");
        }

        return productRepository.findProductsByCategoryEntity(categoryEntity);
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

}
