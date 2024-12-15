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
import org.springframework.http.ResponseEntity;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_PAGE;
import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Endpoints for managing products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/paginated")
    public PagedResponse<ProductDto> findAllWithPagination(
            @RequestParam(required = false) Long supplierId,
            @RequestParam(required = false) List<Long> categoryIds,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.findAllProductsWithPagination(supplierId, categoryIds, minPrice, maxPrice, pageable);
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


    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("productReport", "productReport.pdf");
        return ResponseEntity.ok().headers(headers).body(productService.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("productReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(productService.exportXls());
    }
}
