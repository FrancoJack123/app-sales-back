package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CategoryDto(
        Long id,
        @NotBlank String name,
        String description,
        Set<Long>idsProducts,
        Set<ProductDto> products ) {}
