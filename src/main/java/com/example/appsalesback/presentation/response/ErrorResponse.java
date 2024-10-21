package com.example.appsalesback.presentation.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErrorResponse(
        String code,
        HttpStatus status,
        String message,
        List<String> detailMessages,
        LocalDateTime timeStamp) {}
