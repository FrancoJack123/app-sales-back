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
public interface ProductMapper {
    @Mapping(target = "supplier.products", ignore = true)
    @Mapping(target = "categories", source = "categories", qualifiedByName = "toCategoryDtoSet")
    ProductDto toDto(Product product);

    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "idSupplier", source = "supplier.id")
    @Mapping(target = "idsCategories", source = "categories", qualifiedByName = "toCategoryIds")
    ProductDto toMinimalDto(Product product);

    Product toEntity(ProductDto productDto);

    @Named("toCategoryDtoSet")
    default Set<CategoryDto> toCategoryDtoSet(Set<Category> categories) {
        return categories.stream()
                .map(category ->
                        new CategoryDto(
                                category.getId(),
                                category.getName(),
                                category.getDescription(),
                                null, null))
                .collect(Collectors.toSet());
    }

    @Named("toCategoryIds")
    default Set<Long> toCategoryIds(Set<Category> categories) {
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
    }
}
