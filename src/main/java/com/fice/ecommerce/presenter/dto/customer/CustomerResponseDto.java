package com.fice.ecommerce.presenter.dto.customer;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Builder
@Jacksonized
public record CustomerResponseDto(
        UUID customerReference,
        String fullName,
        String username,
        String email
) {}
