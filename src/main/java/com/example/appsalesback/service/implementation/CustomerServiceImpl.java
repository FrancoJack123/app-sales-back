package com.example.appsalesback.service.implementation;

import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.util.mapper.CustomerMapper;
import com.example.appsalesback.presentation.dto.CustomerDto;
import com.example.appsalesback.persistence.entity.Customer;
import com.example.appsalesback.persistence.repository.CustomerRepository;
import com.example.appsalesback.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public PagedResponse<CustomerDto> findAllCustomersWithPagination(Pageable pageable) {
        Page<CustomerDto> customerPage = customerRepository.findAll(pageable)
                .map(customerMapper::toDto);

        return new PagedResponse<>(
                customerPage.getContent(),
                customerPage.getTotalPages(),
                customerPage.getTotalElements(),
                customerPage.getSize(),
                customerPage.getNumber()
        );
    }

    @Override
    public List<OptionResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new OptionResponse(customer.getId(), customer.getName()))
                .toList();
    }

    @Override
    public CustomerDto findByIdCustomer(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDto)
                .orElseThrow(null);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.toDto(customerRepository.save(customerMapper.toEntity(customerDto)));
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id)
                .map(customer -> {
                    Customer customerEntity = customerMapper.toEntity(customerDto);
                    customerEntity.setId(id);
                    return customerMapper.toDto(customerRepository.save(customerEntity));
                })
                .orElseThrow(null);
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean existsByEmailCustomer(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhoneCustomer(String phone) {
        return customerRepository.existsByPhone(phone);
    }
}
