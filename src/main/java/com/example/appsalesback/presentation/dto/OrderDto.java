package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderDto(
        Long id,
        LocalDate orderDate,
        @NotNull @PositiveOrZero BigDecimal totalAmount,
        @NotNull Long idCustomer,
        @NotNull Set<Long> idsOrderDetails,
        CustomerDto customer,
        Set<OrderDetailDto> orderDetails) {}
