package com.example.appsalesback.service.interfaces;

import com.example.appsalesback.presentation.dto.SupplierDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupplierService {
    PagedResponse<SupplierDto> findAllSuppliersWithPagination(String name, String ruc, Pageable pageable);
    List<OptionResponse> findAllSuppliers();
    SupplierDto findByIdSupplier(Long id);
    SupplierDto saveSupplier(SupplierDto supplierDto);
    SupplierDto updateSupplier(Long id, SupplierDto supplierDto);
    Boolean deleteSupplier(Long id);
    Boolean existsByRucSupplier(String ruc);
}
