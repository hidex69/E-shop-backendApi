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

    @Column(name = "rating_total")
    private Integer ratingTotal;

    @Column(name = "rating_counter")
    private Integer ratingCounter;

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

    public Product(String name, String shortdescription, Integer cost, Integer ratingTotal, Integer rating_counter, CategoryEntity categoryEntity) {
        this.name = name;
        this.shortdescription = shortdescription;
        this.cost = cost;
        this.ratingTotal = ratingTotal;
        this.ratingCounter = ratingCounter;
        this.categoryEntity = categoryEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRatingTotal() {
        return ratingTotal;
    }

    public void setRatingTotal(Integer rating_total) {
        this.ratingTotal = rating_total;
    }

    public Integer getRatingCounter() {
        return ratingCounter;
    }

    public void setRatingCounter(Integer ratingCounter) {
        this.ratingCounter = ratingCounter;
    }
}
