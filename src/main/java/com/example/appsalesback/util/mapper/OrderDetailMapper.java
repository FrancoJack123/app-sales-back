package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.OrderDetail;
import com.example.appsalesback.presentation.dto.OrderDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailDto toDto(OrderDetail orderDetail);
    OrderDetail toEntity(OrderDetailDto orderDetailDto);
}
