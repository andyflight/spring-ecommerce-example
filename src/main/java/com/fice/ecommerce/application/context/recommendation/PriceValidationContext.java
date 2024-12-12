package com.fice.ecommerce.application.context.recommendation;


import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PriceValidationContext {
    Boolean isValidated;
}
