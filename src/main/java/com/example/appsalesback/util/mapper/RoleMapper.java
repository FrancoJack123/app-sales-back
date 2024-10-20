package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.Role;
import com.example.appsalesback.presentation.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
}
