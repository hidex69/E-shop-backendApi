package com.eshop.demo.service;

import com.eshop.demo.DAO.ProductDAO;
import com.eshop.demo.exceptions.NoTokenException;
import com.eshop.demo.models.product.Product;
import com.eshop.demo.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Service
public class BasketService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    public void addToBasket(int product_id, HttpServletRequest request) throws NoTokenException {
        productDAO.loadToBasket(userService.getUserByRequest(request).getId(), product_id);
    }

    public void removeFromBasket(int product_id, HttpServletRequest request) throws NoTokenException {
        productDAO.deleteFromBasket(userService.getUserByRequest(request).getId(), product_id);
    }

    public Set<Product> getAll(HttpServletRequest request) throws NoTokenException {
        return userEntityRepository.findById(userService.getUserByRequest(request).getId()).get().getProducts();
    }
}
