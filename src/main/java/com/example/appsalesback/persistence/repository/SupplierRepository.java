package com.example.appsalesback.persistence.repository;

import com.example.appsalesback.persistence.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Boolean existsByRuc(String ruc);

    @Query("SELECT s FROM Supplier s " +
            "WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT(:name, '%'))) " +
            "AND (:ruc IS NULL OR s.ruc LIKE CONCAT(:ruc, '%'))")
    Page<Supplier> findByAdvancedSearch(
            @Param("name") String name,
            @Param("ruc") String ruc,
            Pageable pageable
    );
}