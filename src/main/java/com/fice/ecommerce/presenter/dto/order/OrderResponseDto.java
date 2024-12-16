package com.fice.ecommerce.presenter.dto.order;

import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.presenter.dto.orderItem.OrderItemResponseDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto (
    UUID orderNumber,
    UUID customerId,
    List<OrderItemResponseDto> orderItems,
    OrderStatus status,
    Date createdAt
) {
}
