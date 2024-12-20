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
@Builder
public class Order {
    Long id;
    UUID orderNumber;
    List<OrderItem> orderItems;
    OrderStatus status;
    Date createdAt;

    public Double getTotalPrice() {
        if (this.orderItems == null || this.orderItems.isEmpty()) {
            throw new EmptyOrderException("OrderItems cannot be null or empty");         }

        return this.orderItems.stream()
                .filter(Objects::nonNull)
                .mapToDouble(orderItem -> {
                    Product product = orderItem.getProduct();
                    if (product == null) {
                        throw new EmptyOrderException("OrderItem product cannot be null");
                    }
                    return product.getPrice() * orderItem.getQuantity();
                })
                .sum();
    }

}
