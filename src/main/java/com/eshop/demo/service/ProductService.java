package com.eshop.demo.service;

import com.eshop.demo.DAO.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void setRating(int product_id, int rate) {
        productDAO.rateProduct(product_id, rate);
    }
}
