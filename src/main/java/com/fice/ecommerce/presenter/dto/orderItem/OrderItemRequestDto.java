package com.fice.ecommerce.presenter.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDto(
    @NotNull(message = "Product code cannot be null")
    @Pattern(regexp = "\\d{12,13}", message = "Product code supports only upc/ean format")
    String productCode,
    
    @NotNull(message = "Products quantity cannot be null")
    @Positive(message = "Products quantity must be greater than zero")
    Integer quantity,
    
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than zero")
    Double productOldPrice
) {
}
