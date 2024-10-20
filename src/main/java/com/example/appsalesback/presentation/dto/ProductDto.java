package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductDto(
        Long id,
        @NotBlank String name,
        String description,
        String imgUrl,
        @Positive BigDecimal price,
        @NotNull BigInteger stock,
        @NotNull Long idSupplier,
        @NotNull Set<Long> idsCategories,
        SupplierDto supplier,
        Set<CategoryDto> categories) {}

