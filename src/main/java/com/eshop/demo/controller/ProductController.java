package com.eshop.demo.controller;

import com.eshop.demo.DAO.ProductDAO;
import com.eshop.demo.models.Product;
import com.eshop.demo.models.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/")
    public List<Product> loadProducts() {
        return productDAO.loadAllProducts();
    }

    @GetMapping("/{id}")
    public Product loadProduct(@PathVariable int id) {
        return productDAO.loadProduct(id);
    }
    
    @PostMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id, HttpServletResponse response) throws IOException {
        productDAO.deleteProduct(id);
        response.sendRedirect("/product");
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto, HttpServletResponse response) throws IOException {
        productDAO.addProduct(productDto);
        response.sendRedirect("/product");
    }
    
    @PostMapping("/update/{id}")
    public void changeProduct(@RequestBody ProductDto productDto,
                              @PathVariable int id, HttpServletResponse response) throws IOException {
        productDAO.updateProduct(productDto, id);
        response.sendRedirect("/product");
    }

}
