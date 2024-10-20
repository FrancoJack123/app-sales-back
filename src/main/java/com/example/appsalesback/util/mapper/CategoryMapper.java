package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.Category;
import com.example.appsalesback.persistence.entity.Product;
import com.example.appsalesback.presentation.dto.CategoryDto;
import com.example.appsalesback.presentation.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "products", source = "products", qualifiedByName = "toProductDtoSet")
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto dto);

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
