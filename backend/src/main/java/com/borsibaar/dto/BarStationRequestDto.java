package com.borsibaar.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BarStationRequestDto(
    @NotBlank @Size(max = 100, message="Name must be at most 100 characters") String name,

    @Size(max = 500, message="Description must be at most 500 characters") String description,

    @NotNull(message="isActive cannot be null") Boolean isActive,

    @NotNull(message="userIds cannot be null") List<UUID> userIds
) {
}

