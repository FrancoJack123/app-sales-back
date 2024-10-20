package com.example.appsalesback.service.interfaces;


import com.example.appsalesback.presentation.dto.ProductDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    PagedResponse<ProductDto> findAllProductsWithPagination(Pageable pageable);
    List<OptionResponse> findAllProducts();
    ProductDto findByIdProduct(Long id);
    ProductDto saveProduct(ProductDto productDto);
    ProductDto updateProduct(Long id, ProductDto productDto);
    Boolean deleteProduct(Long id);
}
