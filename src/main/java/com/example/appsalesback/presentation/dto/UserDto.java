package com.example.appsalesback.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UserDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @Email @NotBlank String email,
        @NotNull Set<String> rolesNames,
        Boolean accountNonExpired,
        Boolean accountNonLocked,
        Boolean credentialsNonExpired,
        Boolean enabled,
        Set<RoleDto> roles){}
