package com.eshop.demo.service;

import com.eshop.demo.models.category.CategoryDto;
import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.repository.CategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryEntityRepository repository;

    public List<CategoryEntity> loadAllCategories() {
        return repository.findAll();
    }

    public void add(CategoryDto categoryDto) {
        repository.save(new CategoryEntity(categoryDto.getName().toUpperCase()));
    }
}
