package com.fice.ecommerce.application.exceptions;

public class CategoryPartialResultException extends RuntimeException {
    public CategoryPartialResultException(String message) {
        super(String.format("The following categories were not found: %s", message));
    }
}
