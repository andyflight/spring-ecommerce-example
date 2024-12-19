package com.fice.ecommerce.presenter.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
@Schema(description = "Response containing product details")
public record ProductResponseDto(
        @Schema(description = "UPC/EAN code of the product", example = "123456789012")
        String code,

        @Schema(description = "Name of the product", example = "Smartphone")
        String name,

        @Schema(description = "Description of the product", example = "Latest model smartphone with 128GB storage")
        String description,

        @Schema(description = "Price of the product", example = "699.99")
        Double price,

        @Schema(description = "List of category names the product belongs to")
        List<String> categoryNames
) {
}
