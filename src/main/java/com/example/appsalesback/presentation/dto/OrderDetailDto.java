package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDetailDto(
        Long id,
        @NotNull BigInteger quantity,
        @Positive BigDecimal unitPrice,
        @Positive BigDecimal totalPrice,
        @NotNull Long idProduct,
        @NotNull Long idOrder,
        ProductDto product) {}
