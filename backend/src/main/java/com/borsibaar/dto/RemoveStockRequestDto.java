package com.borsibaar.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record RemoveStockRequestDto(
                @NotNull(message = "Product ID is required") Long productId,

                @NotNull(message = "Quantity is required") @DecimalMin(value = "0.0", message = "Quantity must be greater than 0") BigDecimal quantity,

                @NotNull(message = "referenceId is required") String referenceId,

                @Size(max = 500, message = "Notes must be at most 500 characters") String notes) {
}
