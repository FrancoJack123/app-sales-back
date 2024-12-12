package com.example.appsalesback.service.implementation;

import com.example.appsalesback.persistence.entity.Supplier;
import com.example.appsalesback.persistence.repository.SupplierRepository;
import com.example.appsalesback.presentation.dto.SupplierDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.exception.SupplierNotFoundException;
import com.example.appsalesback.service.interfaces.SupplierService;
import com.example.appsalesback.util.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public PagedResponse<SupplierDto> findAllSuppliersWithPagination(String name, String ruc, Pageable pageable) {
        Page<SupplierDto> supplierPage = supplierRepository.findByAdvancedSearch(name, ruc, pageable)
                .map(supplierMapper::toDto);

        return new PagedResponse<>(
                supplierPage.getContent(),
                supplierPage.getTotalPages(),
                supplierPage.getTotalElements(),
                supplierPage.getSize(),
                supplierPage.getNumber()
        );
    }

    @Override
    public List<OptionResponse> findAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(customer -> new OptionResponse(customer.getId(), customer.getName()))
                .toList();
    }

    @Override
    public SupplierDto findByIdSupplier(Long id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toDto)
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    public SupplierDto saveSupplier(SupplierDto supplierDto) {
        return supplierMapper.toDto(supplierRepository.save(supplierMapper.toEntity(supplierDto)));
    }

    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    Supplier supplierEntity = supplierMapper.toEntity(supplierDto);
                    supplier.setId(id);
                    return supplierMapper.toDto(supplierRepository.save(supplierEntity));
                })
                .orElseThrow(SupplierNotFoundException::new);
    }

    @Override
    public Boolean deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean existsByRucSupplier(String ruc) {
        return supplierRepository.existsByRuc(ruc);
    }
}
