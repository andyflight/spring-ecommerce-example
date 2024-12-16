package com.fice.ecommerce.application.context.order;

import com.fice.ecommerce.domain.OrderItem;
import com.fice.ecommerce.domain.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderItemContext {
    String productCode;
    Integer quantity;
    Double productOldPrice;
    
    public OrderItem toOrderItem() {
        return OrderItem.builder()
            .quantity(getQuantity())
            .product(
                Product.builder()
                .code(getProductCode())
                .build()
            )
            .productOldPrice(getProductOldPrice())
            .build();
    }
}
