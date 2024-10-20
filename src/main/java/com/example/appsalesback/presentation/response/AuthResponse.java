package com.example.appsalesback.presentation.response;

public record AuthResponse(
        String username,
        String jwt,
        Boolean status) {}
