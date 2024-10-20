package com.example.appsalesback.presentation.controller;

import com.example.appsalesback.presentation.dto.ProductDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.interfaces.ProductService;
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
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Products", description = "Endpoints for managing products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/paginated")
    public PagedResponse<ProductDto> findAllWithPagination(
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAllProductsWithPagination(pageable);
    }

    @GetMapping()
    public List<OptionResponse> findAll() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findByIdProduct(id);
    }

    @PostMapping()
    public ProductDto save(@Valid @RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
