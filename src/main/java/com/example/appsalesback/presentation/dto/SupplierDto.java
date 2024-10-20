package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SupplierDto(
        Long id,
        @NotBlank String name,
        @NotBlank String ruc,
        @NotBlank String contactInfo,
        Set<Long> idsProducts,
        Set<ProductDto> products) {}
