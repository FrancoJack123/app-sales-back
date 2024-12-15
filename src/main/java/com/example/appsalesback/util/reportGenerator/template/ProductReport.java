package com.example.appsalesback.util.reportGenerator.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReport {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigInteger stock;
}
