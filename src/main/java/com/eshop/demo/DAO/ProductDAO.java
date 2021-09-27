package com.eshop.demo.DAO;

import com.eshop.demo.models.Product;
import com.eshop.demo.models.ProductDto;
import com.eshop.demo.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Product> loadAllProducts() {

        String sql = "select * from product";

        List<Product> products = jdbcTemplate.query(sql, new ProductRowMapper());

        return products;

    }

    public Product loadProduct(int id) {
        String sql = "select * from product where id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
    }

    public void deleteProduct(int id) {
        String sql = "delete from product where id = ?";

        jdbcTemplate.update(sql, id);
    }

    public void addProduct(ProductDto productDto) {
        String sql = "insert into product (name, shortdescription, cost) values(?, ?, ?)";

        jdbcTemplate.update(sql, productDto.getName(), productDto.getShortDescription(), productDto.getCost());
    }

}
