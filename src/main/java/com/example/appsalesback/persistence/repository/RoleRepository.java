package com.example.appsalesback.persistence.repository;

import com.example.appsalesback.persistence.entity.Role;
import com.example.appsalesback.persistence.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}