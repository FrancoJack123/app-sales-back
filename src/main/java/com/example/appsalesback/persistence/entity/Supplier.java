package com.example.appsalesback.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String ruc;
    @Column(name = "contact_info")
    private String contactInfo;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();
}
