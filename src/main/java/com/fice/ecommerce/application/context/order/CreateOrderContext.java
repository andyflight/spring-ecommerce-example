package com.fice.ecommerce.application.context.order;

import com.fice.ecommerce.common.OrderStatus;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CreateOrderContext {
    UUID customerReference;
    List<OrderItemContext> orderItems;
    OrderStatus orderStatus;
}
