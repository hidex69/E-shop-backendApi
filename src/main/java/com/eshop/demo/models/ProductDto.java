package com.eshop.demo.models;

public class ProductDto {
    private String name;
    private String shortDescription;
    private int cost;

    public ProductDto(String name, String shortDescription, int cost) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.cost = cost;
    }

    public ProductDto() {
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
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", cost=" + cost +
                '}';
    }
}
