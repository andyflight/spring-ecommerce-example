package com.fice.ecommerce.application.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(String.format("Product with code %s not found", message));
    }
}
