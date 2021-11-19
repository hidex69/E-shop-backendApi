package com.eshop.demo.models.category;


import javax.persistence.*;

@Entity
@Table(name = "product_category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public CategoryEntity() {
    }
}
