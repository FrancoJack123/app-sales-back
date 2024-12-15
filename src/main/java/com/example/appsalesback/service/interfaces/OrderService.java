package com.example.appsalesback.service.interfaces;

import com.example.appsalesback.presentation.dto.OrderDto;
import com.example.appsalesback.presentation.response.PagedResponse;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    PagedResponse<OrderDto> findAllOrders(Pageable pageable);
    OrderDto findByIdOrder(Long idOrder);
    OrderDto saveOrder(OrderDto orderDto);
}
