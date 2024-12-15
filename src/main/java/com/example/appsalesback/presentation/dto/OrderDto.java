package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDto(
        Long id,
        LocalDate orderDate,
        @NotNull @PositiveOrZero BigDecimal totalAmount,
        @NotNull Long idCustomer,
        CustomerDto customer,
        Set<OrderDetailDto> orderDetails) {}
