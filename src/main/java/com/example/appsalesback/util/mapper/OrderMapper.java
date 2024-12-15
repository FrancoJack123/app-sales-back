package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.Order;
import com.example.appsalesback.presentation.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "idCustomer", source = "customer.id")
    @Mapping(target = "orderDetails.idOrder", ignore = true)
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
