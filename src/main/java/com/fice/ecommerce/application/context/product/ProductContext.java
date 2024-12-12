package com.fice.ecommerce.application.context.product;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProductContext {
    String code;
    String name;
    String description;
    Double price;
    List<String> categoryNames;
}
