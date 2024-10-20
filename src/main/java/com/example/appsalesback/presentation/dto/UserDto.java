package com.example.appsalesback.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @Email @NotBlank String email,
        Set<String> rolesNames,
        Set<RoleDto> roles){}
