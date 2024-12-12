package com.fice.ecommerce.presenter.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record SimpleResponse(
        String message
) {
}
