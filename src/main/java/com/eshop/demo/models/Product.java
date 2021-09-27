package com.eshop.demo.models;

public class Product {
    private int id;
    private String name;
    private String shortDescription;
    private int cost;

    public Product() {
    }

    public Product(int id, String name, String shortDescription, int cost) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", cost=" + cost +
                '}';
    }
}
