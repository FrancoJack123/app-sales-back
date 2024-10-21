package com.example.appsalesback.service.implementation;

import com.example.appsalesback.persistence.entity.Category;
import com.example.appsalesback.persistence.repository.CategoryRepository;
import com.example.appsalesback.presentation.dto.CategoryDto;
import com.example.appsalesback.presentation.dto.CustomerDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.exception.CategoryNotFoundException;
import com.example.appsalesback.service.interfaces.CategoryService;
import com.example.appsalesback.util.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public PagedResponse<CategoryDto> findAllCategoriesWithPagination(Pageable pageable) {
        Page<CategoryDto> categoryPage = categoryRepository.findAll(pageable)
                .map(categoryMapper::toDto);

        return new PagedResponse<>(
                categoryPage.getContent(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements(),
                categoryPage.getSize(),
                categoryPage.getNumber()
        );
    }

    @Override
    public List<OptionResponse> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(customer -> new OptionResponse(customer.getId(), customer.getName()))
                .toList();
    }

    @Override
    public CategoryDto findByIdCategory(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return categoryRepository.findById(id)
                .map(category -> {
                    Category categoryEntity = categoryMapper.toEntity(categoryDto);
                    categoryEntity.setId(category.getId());
                    return categoryMapper.toDto(categoryRepository.save(categoryEntity));
                })
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
