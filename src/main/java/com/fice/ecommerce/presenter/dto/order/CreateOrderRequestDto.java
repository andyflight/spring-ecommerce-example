package com.fice.ecommerce.presenter.dto.order;

import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.presenter.dto.orderItem.OrderItemRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Builder
@Jacksonized
public record CreateOrderRequestDto(
    @NotNull(message = "Customer reference cannot be null")
    @UUID(message = "Customer reference must be in UUID format")
    String customerReference,
    
    @NotNull(message = "Order items cannot be null")
    List<@Valid OrderItemRequestDto> orderItems,
    
    @NotNull(message = "Order status cannot be null")
    OrderStatus orderStatus
) {
}
