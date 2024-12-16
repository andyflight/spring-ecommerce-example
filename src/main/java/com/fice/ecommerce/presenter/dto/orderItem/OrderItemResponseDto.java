package com.fice.ecommerce.presenter.dto.orderItem;

public record OrderItemResponseDto (
    Integer quantity,
    String productCode,
    Double productOldPrice
) {
}
