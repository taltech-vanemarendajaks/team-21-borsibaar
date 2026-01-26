package com.borsibaar.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AdjustStockRequestDto(
                @NotNull(message = "Product ID is required") Long productId,

                @NotNull(message = "New quantity is required") @DecimalMin(value = "0", message = "Quantity cannot be negative") BigDecimal newQuantity,

                @Size(max = 500, message = "Notes must be at most 500 characters") String notes) {
}
