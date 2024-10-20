package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.Product;
import com.example.appsalesback.persistence.entity.Supplier;
import com.example.appsalesback.presentation.dto.ProductDto;
import com.example.appsalesback.presentation.dto.SupplierDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    @Mapping(target = "products", source = "products", qualifiedByName = "toProductDtoSet")
    SupplierDto toDto(Supplier supplier);

    Supplier toEntity(SupplierDto supplierDto);

    @Named("toProductDtoSet")
    default Set<ProductDto> toProductDtoSet(Set<Product> products) {
        if (products == null) {
            return Set.of();
        }
        return products.stream()
                .map(product ->
                        new ProductDto(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getImgUrl(),
                                product.getPrice(),
                                product.getStock(),
                                null, null, null, null
                        ))
                .collect(Collectors.toSet());
    }
}
