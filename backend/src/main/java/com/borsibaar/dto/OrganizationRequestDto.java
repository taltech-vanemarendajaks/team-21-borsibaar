package com.borsibaar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

import io.micrometer.common.lang.NonNull;

public record OrganizationRequestDto(
        @NotBlank @Size(max = 100, message="Name must be at most 100 characters") String name,
        @NonNull(message="Price increase step is required") @DecimalMin("0.0") BigDecimal priceIncreaseStep,
        @NonNull(message="Price decrease step is required") @DecimalMin("0.0") BigDecimal priceDecreaseStep) {
}
