package com.fice.ecommerce.presenter.dto.order;

import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.presenter.dto.orderItem.OrderItemRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record UpdateOrderRequestDto(
    @NotNull(message = "Order status cannot be null")
    OrderStatus orderStatus
) {
}
