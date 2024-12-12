package com.example.appsalesback.persistence.repository;

import com.example.appsalesback.persistence.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);

    @Query("SELECT c FROM Customer c " +
            "WHERE (:name IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT(:name, '%'))) " +
            "AND (:email IS NULL OR LOWER(c.email) LIKE LOWER(CONCAT(:email, '%'))) " +
            "AND (:phone IS NULL OR c.phone LIKE CONCAT(:phone, '%'))")
    Page<Customer> findByAdvancedSearch(
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            Pageable pageable
    );
}
