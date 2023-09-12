package com.example.inditexttest.application.service.prices.exception;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(Integer productId) {
        super("Not found product with productId " + productId);
    }
}
