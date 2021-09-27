package com.eshop.demo.DAO;

import com.eshop.demo.models.Product;
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

}
