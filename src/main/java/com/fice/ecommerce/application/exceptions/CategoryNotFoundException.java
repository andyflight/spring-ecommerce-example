package com.fice.ecommerce.application.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(String.format("Category with name %s not found", message));
    }
}
