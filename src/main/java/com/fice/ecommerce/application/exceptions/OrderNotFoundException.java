package com.fice.ecommerce.application.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(String.format("Order with number %s not found", message));
    }
}
