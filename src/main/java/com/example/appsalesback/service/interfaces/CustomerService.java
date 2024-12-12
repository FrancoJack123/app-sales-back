package com.example.appsalesback.service.interfaces;

import com.example.appsalesback.presentation.dto.CustomerDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    PagedResponse<CustomerDto> findAllCustomersWithPagination(
            String name, String email, String phone, Pageable pageable);
    List<OptionResponse> findAllCustomers();
    CustomerDto findByIdCustomer(Long id);
    CustomerDto saveCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    Boolean deleteCustomer(Long id);
    Boolean existsByEmailCustomer(String email);
    Boolean existsByPhoneCustomer(String phone);
}
