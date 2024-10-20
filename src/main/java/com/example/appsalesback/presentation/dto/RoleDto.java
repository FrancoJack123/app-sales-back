package com.example.appsalesback.presentation.dto;

import com.example.appsalesback.persistence.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public record RoleDto(
        RoleName roleName){}
