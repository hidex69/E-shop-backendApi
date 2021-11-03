package com.eshop.demo.models.product;

public class RatingRequest {

    private int product_id;
    private int rating;

    public int getProduct_id() {
        return product_id;
    }

    public int getRating() {
        return rating;
    }

    public RatingRequest(int product_id, int rating) {
        this.product_id = product_id;
        this.rating = rating;
    }
}
