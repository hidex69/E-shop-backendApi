package com.eshop.demo.controller;

import com.eshop.demo.DAO.ProductDAO;
import com.eshop.demo.models.Product;
import com.eshop.demo.models.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    
    @PostMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable int id, HttpServletResponse response) throws IOException {
        productDAO.deleteProduct(id);
        response.sendRedirect("/product");
    }

    @PostMapping("/product/add")
    public void addProduct(@RequestBody ProductDto productDto, HttpServletResponse response) throws IOException {
        productDAO.addProduct(productDto);
        response.sendRedirect("/product");
    }
    
    @PostMapping("product/update/{id}")
    public void changeProduct(@RequestBody ProductDto productDto,
                              @PathVariable int id, HttpServletResponse response) throws IOException {
        productDAO.updateProduct(productDto, id);
        response.sendRedirect("/product");
    }

}
