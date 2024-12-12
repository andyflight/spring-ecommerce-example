package com.fice.ecommerce.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderItem {
    Long id;
    Product product;
    Integer quantity;

}
