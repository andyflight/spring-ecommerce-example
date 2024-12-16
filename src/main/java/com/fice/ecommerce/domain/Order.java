package com.fice.ecommerce.domain;

import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.domain.exceptions.EmptyOrderException;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Order {
    Long id;
    UUID orderNumber;
    Customer customer;
    List<OrderItem> orderItems;
    OrderStatus status;
    Date createdAt;

    public Double getTotalPrice() {
        if (this.orderItems == null || this.orderItems.isEmpty()) {
            throw new EmptyOrderException("OrderItems cannot be null or empty");         }

        return this.orderItems.stream()
                .filter(Objects::nonNull)
                .mapToDouble(orderItem -> orderItem.getProductOldPrice() * orderItem.getQuantity())
                .sum();
    }

}
