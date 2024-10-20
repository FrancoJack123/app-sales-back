package com.example.appsalesback.persistence.repository;

import com.example.appsalesback.persistence.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsByRuc(String ruc);
}