package com.eshop.demo.controller;

import com.eshop.demo.models.category.CategoryDto;
import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.loadAllCategories();
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.add(categoryDto);
    }
}
