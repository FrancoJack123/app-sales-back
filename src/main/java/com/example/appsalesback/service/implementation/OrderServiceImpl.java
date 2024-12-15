package com.example.appsalesback.service.implementation;

import com.example.appsalesback.persistence.repository.OrderRepository;
import com.example.appsalesback.presentation.dto.OrderDto;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.interfaces.OrderService;
import com.example.appsalesback.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public PagedResponse<OrderDto> findAllOrders(Pageable pageable) {
        Page<OrderDto> orderPage = orderRepository.findAll(pageable).map(orderMapper::toDto);

        return new PagedResponse<>(
                orderPage.getContent(),
                orderPage.getTotalPages(),
                orderPage.getTotalElements(),
                orderPage.getSize(),
                orderPage.getNumber()
        );
    }

    @Override
    public OrderDto findByIdOrder(Long idOrder) {
        return orderRepository.findById(idOrder)
                .map(orderMapper::toDto)
                .orElse(null);
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        return null;
    }
}
