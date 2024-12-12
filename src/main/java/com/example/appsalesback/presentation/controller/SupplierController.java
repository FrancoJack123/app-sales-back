package com.example.appsalesback.presentation.controller;

import com.example.appsalesback.presentation.dto.SupplierDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.interfaces.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_PAGE;
import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
@Tag(name = "Suppliers", description = "Endpoints for managing suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/paginated")
    public PagedResponse<SupplierDto> findAllWithPagination(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String ruc,
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return supplierService.findAllSuppliersWithPagination(name, ruc, pageable);
    }

    @GetMapping()
    public List<OptionResponse> findAll() {
        return supplierService.findAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierDto findById(@PathVariable Long id) {
        return supplierService.findByIdSupplier(id);
    }

    @GetMapping("/exists/ruc/{ruc}")
    public Boolean existsByRuc(@PathVariable String ruc) {
        return supplierService.existsByRucSupplier(ruc);
    }

    @PostMapping()
    public SupplierDto save(@Valid @RequestBody SupplierDto supplierDto) {
        return supplierService.saveSupplier(supplierDto);
    }

    @PutMapping("/{id}")
    public SupplierDto update(@PathVariable Long id, @Valid @RequestBody SupplierDto supplierDto) {
        return supplierService.updateSupplier(id, supplierDto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return supplierService.deleteSupplier(id);
    }
}
