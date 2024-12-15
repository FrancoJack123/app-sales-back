package com.example.appsalesback.util.reportGenerator.template;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierReport {
    private Long id;
    private String name;
    private String ruc;
    private String contactInfo;
}
