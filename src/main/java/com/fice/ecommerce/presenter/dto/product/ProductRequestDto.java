package com.fice.ecommerce.presenter.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;


@Builder
@Jacksonized
@Schema(description = "Request to create or update a product")
public record ProductRequestDto(
        @NotNull(message = "Product code cannot be null")
        @Pattern(regexp = "\\d{12,13}", message = "Product code supports only upc/ean format")
        @Schema(description = "UPC/EAN code of the product", example = "123456789012")
        String code,

        @NotBlank(message = "Name cannot be blank")
        @Size(max=100, message = "Name cannot be longer than 100 characters")
        @Schema(description = "Name of the product", example = "Smartphone")
        String name,

        @NotBlank(message = "Description cannot be blank")
        @Size(max=255, message = "Description cannot be longer than 255 characters")
        @Schema(description = "Description of the product", example = "Latest model smartphone with 128GB storage")
        String description,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be greater than zero")
        @Schema(description = "Price of the product", example = "699.99")
        Double price,

        @NotNull
        @Schema(description = "List of category names the product belongs to")
        List<String> categoryNames
) {
}
