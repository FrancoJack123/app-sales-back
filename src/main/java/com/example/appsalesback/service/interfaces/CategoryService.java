package com.example.appsalesback.service.interfaces;


import com.example.appsalesback.presentation.dto.CategoryDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    PagedResponse<CategoryDto> findAllCategoriesWithPagination(Pageable pageable);
    List<OptionResponse> findAllCategories();
    CategoryDto findByIdCategory(Long id);
    CategoryDto saveCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    Boolean deleteCategory(Long id);
}
