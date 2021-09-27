package com.eshop.demo.controller;

import com.eshop.demo.DAO.ProductDAO;
import com.eshop.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/product")
    public List<Product> loadProducts() {
        return productDAO.loadAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product loadProduct(@PathVariable int id) {
        return productDAO.loadProduct(id);
    }

}
