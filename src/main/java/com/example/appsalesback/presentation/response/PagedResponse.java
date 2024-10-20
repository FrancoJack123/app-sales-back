package com.example.appsalesback.presentation.response;

import java.util.List;

public record PagedResponse<T>(
        List<T> content,
        Integer totalPages,
        Long totalElements,
        Integer size,
        Integer page) {
}
