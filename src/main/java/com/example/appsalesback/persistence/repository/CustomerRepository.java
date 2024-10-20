package com.example.appsalesback.persistence.repository;

import com.example.appsalesback.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
