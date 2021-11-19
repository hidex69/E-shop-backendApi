package com.eshop.demo.models.product;

import com.eshop.demo.models.category.CategoryEntity;
import com.eshop.demo.models.user.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String shortdescription;

    @Column
    private Integer cost;

    @Column
    private Integer rating_total;

    @Column
    private Integer rating_counter;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToMany(mappedBy = "products")
    private Set<UserEntity> users = new HashSet<>();

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Product(String name, String shortdescription, Integer cost, Integer rating_total, Integer rating_counter, CategoryEntity categoryEntity) {
        this.name = name;
        this.shortdescription = shortdescription;
        this.cost = cost;
        this.rating_total = rating_total;
        this.rating_counter = rating_counter;
        this.categoryEntity = categoryEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRating_total() {
        return rating_total;
    }

    public void setRating_total(Integer rating_total) {
        this.rating_total = rating_total;
    }

    public Integer getRating_counter() {
        return rating_counter;
    }

    public void setRating_counter(Integer rating_counter) {
        this.rating_counter = rating_counter;
    }
}
