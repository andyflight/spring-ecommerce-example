package com.fice.ecommerce.presenter.dto.order;

import com.fice.ecommerce.common.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Schema(description = "Request to update the status of an order")
public record UpdateOrderRequestDto(
    @NotNull(message = "Order status cannot be null")
    @Schema(description = "New status for the order", example = "PROCESSING")
    OrderStatus orderStatus
) {
}
