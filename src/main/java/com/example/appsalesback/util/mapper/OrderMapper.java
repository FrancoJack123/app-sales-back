package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.Order;
import com.example.appsalesback.presentation.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
