package com.example.appsalesback.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDto(
        Long id,
        @NotBlank String name,
        @Email @NotBlank String email,
        @Size(min = 9) String phone,
        @NotBlank String address,
        String city,
        String state,
        String zip) {}
