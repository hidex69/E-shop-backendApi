package com.eshop.demo.rowmapper;

import com.eshop.demo.models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();

        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setShortDescription(resultSet.getString("shortDescription"));
        product.setCost(resultSet.getInt("cost"));

        return product;
    }
}
