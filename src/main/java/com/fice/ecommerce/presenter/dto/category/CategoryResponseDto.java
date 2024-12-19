package com.fice.ecommerce.presenter.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response containing the category details")
public record CategoryResponseDto(
        @Schema(description = "Name of the category", example = "Electronics")
        String name
) {
}
