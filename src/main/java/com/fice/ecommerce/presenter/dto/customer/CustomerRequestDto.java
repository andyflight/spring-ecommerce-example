package com.fice.ecommerce.presenter.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CustomerRequestDto(
        @NotBlank(message = "Full name cannot be blank")
        String fullName,

        @NotBlank(message = "Username cannot be blank")
        String username,

        @NotBlank(message = "Password cannot be blank")
        String password,

        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        String email
) {}