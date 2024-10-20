package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InvoiceDto(
        Long id,
        @Pattern(regexp = "c") @NotBlank String invoiceNumber,
        LocalDate billingDate,
        @NotNull @Positive BigDecimal totalAmount,
        OrderDto order) {}
