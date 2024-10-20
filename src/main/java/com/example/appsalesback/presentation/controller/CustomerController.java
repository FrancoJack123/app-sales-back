package com.example.appsalesback.presentation.controller;

import com.example.appsalesback.presentation.dto.CustomerDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_PAGE;
import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Customers", description = "Endpoints for managing customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/paginated")
    public PagedResponse<CustomerDto> findAllWithPagination(
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return customerService.findAllCustomersWithPagination(pageable);
    }

    @GetMapping()
    public List<OptionResponse> findAll() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto findById(@PathVariable Long id) {
        return customerService.findByIdCustomer(id);
    }

    @GetMapping("/exists/email/{email}")
    public Boolean existsByEmail(@PathVariable String email) {
        return customerService.existsByEmailCustomer(email);
    }

    @GetMapping("/exists/phone/{phone}")
    public Boolean existsByPhone(@PathVariable String phone) {
        return customerService.existsByPhoneCustomer(phone);
    }

    @PostMapping()
    public CustomerDto save(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
