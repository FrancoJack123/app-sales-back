package com.example.appsalesback.util.mapper;

import com.example.appsalesback.presentation.dto.CustomerDto;
import com.example.appsalesback.persistence.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
}
