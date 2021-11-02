package com.eshop.demo.controller;

import com.eshop.demo.DAO.ProductDAO;
import com.eshop.demo.models.product.Product;
import com.eshop.demo.models.product.ProductDto;
import com.eshop.demo.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {

    //TODO: method to rate a product

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private BasketService basketService;

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
    public void addProduct(@RequestBody ProductDto productDto) {
        productDAO.addProduct(productDto);
    }
    
    @PostMapping("/update/{id}")
    public void changeProduct(@RequestBody ProductDto productDto,
                              @PathVariable int id) {
        productDAO.updateProduct(productDto, id);
    }

    @PostMapping("/basket/add/{id}")
    public void addToBasket(@PathVariable int id, HttpServletRequest request) {
        basketService.addToBasket(id, request);
    }

    @GetMapping("/basket/show")
    public Set<Product> getUserBasket(HttpServletRequest request) {
        return basketService.getAll(request);
    }

    @PostMapping
    public void seProductRating() {

    }
}
