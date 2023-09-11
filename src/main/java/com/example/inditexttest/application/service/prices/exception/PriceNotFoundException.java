package com.example.inditexttest.application.service.prices.exception;

public class PriceNotFoundException extends Exception {
    public PriceNotFoundException() {
        super("Not found price for given parameters.");
    }
}
