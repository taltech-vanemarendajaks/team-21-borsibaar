package com.borsibaar.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record OrganizationRequestDto(
        @NotBlank @Size(max = 100, message="Name must be at most 100 characters") String name,
        @NotNull(message="Price increase step is required") @DecimalMin("0.0") BigDecimal priceIncreaseStep,
        @NotNull(message="Price decrease step is required") @DecimalMin("0.0") BigDecimal priceDecreaseStep) {
}
