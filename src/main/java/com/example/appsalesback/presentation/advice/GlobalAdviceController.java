package com.example.appsalesback.presentation.advice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.appsalesback.presentation.response.ErrorResponse;
import com.example.appsalesback.service.exception.CategoryNotFoundException;
import com.example.appsalesback.service.exception.CustomerNotFoundException;
import com.example.appsalesback.service.exception.ProductNotFoundException;
import com.example.appsalesback.service.exception.SupplierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalAdviceController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException exception) {
        return ErrorResponse.builder()
                .code("PRODUCT_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND)
                .message("El producto que busca no existe o ya no está disponible.")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return ErrorResponse.builder()
                .code("CATEGORY_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND)
                .message("La categoría solicitada no fue encontrada.")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SupplierNotFoundException.class)
    public ErrorResponse handleSupplierNotFoundException(SupplierNotFoundException exception) {
        return ErrorResponse.builder()
                .code("SUPPLIER_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND)
                .message("El proveedor solicitado no fue encontrado.")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return ErrorResponse.builder()
                .code("CUSTOMER_NOT_FOUND")
                .status(HttpStatus.NOT_FOUND)
                .message("El cliente solicitado no fue encontrado.")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(JWTVerificationException.class)
    public ErrorResponse handleJWTVerificationException(JWTVerificationException exception) {
        return ErrorResponse.builder()
                .code("FORBIDDEN")
                .status(HttpStatus.FORBIDDEN)
                .message("Token inválido, no autorizado")
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();

        return ErrorResponse.builder()
                .code("INVALID_REQUEST")
                .status(HttpStatus.BAD_REQUEST)
                .message("La solicitud contiene errores en los campos proporcionados.")
                .detailMessages(result.getFieldErrors()
                        .stream()
                        .map(fieldError ->
                                fieldError.getField().toUpperCase() + ": " + fieldError.getDefaultMessage())
                        .collect(Collectors.toList()))
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(Exception exception) {
        return ErrorResponse.builder()
                .code("INTERNAL_SERVER_ERROR")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .detailMessages(Collections.singletonList(exception.getMessage()))
                .timeStamp(LocalDateTime.now())
                .build();
    }
}
