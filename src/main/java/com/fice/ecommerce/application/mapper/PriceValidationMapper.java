package com.fice.ecommerce.application.mapper;

import com.fice.ecommerce.application.context.recommendation.PriceValidationContext;
import com.fice.ecommerce.application.context.recommendation.PriceValidationRequest;
import com.fice.ecommerce.application.context.recommendation.PriceValidationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceValidationMapper {

    PriceValidationRequest toRequest(Double price);

    PriceValidationContext toContext(PriceValidationResponse response);

}
