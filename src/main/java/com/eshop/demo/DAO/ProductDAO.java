package com.eshop.demo.DAO;

import com.eshop.demo.models.product.Product;
import com.eshop.demo.models.product.ProductDto;
import com.eshop.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;


    public List<Product> loadAllProducts() {

        return productRepository.findAll();

    }

    public Product loadProduct(int id) {

        return productRepository.findById(id).get();
    }

    public void deleteProduct(int id) {
        String sql = "delete from product where id = ?";

        jdbcTemplate.update(sql, id);
    }

    public void addProduct(ProductDto productDto) {
        String sql = "insert into product (name, shortdescription, cost) values(?, ?, ?)";

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

}
