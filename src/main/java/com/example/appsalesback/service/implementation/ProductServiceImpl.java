package com.example.appsalesback.service.implementation;

import com.example.appsalesback.persistence.entity.Category;
import com.example.appsalesback.persistence.entity.Product;
import com.example.appsalesback.persistence.entity.Supplier;
import com.example.appsalesback.persistence.repository.CategoryRepository;
import com.example.appsalesback.persistence.repository.ProductRepository;
import com.example.appsalesback.persistence.repository.SupplierRepository;
import com.example.appsalesback.presentation.dto.ProductDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.exception.CategoryNotFoundException;
import com.example.appsalesback.service.exception.ProductNotFoundException;
import com.example.appsalesback.service.exception.SupplierNotFoundException;
import com.example.appsalesback.service.interfaces.ProductService;
import com.example.appsalesback.util.mapper.ProductMapper;
import com.example.appsalesback.util.reportGenerator.ProductReportGenerate;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductReportGenerate productReportGenerate;
    private final ProductMapper productMapper;

    @Override
    public PagedResponse<ProductDto> findAllProductsWithPagination(
            Long supplierId, List<Long> categoryIds, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable)
    {
        Page<ProductDto> productPage = productRepository
                .findByAdvancedSearch(supplierId, categoryIds, minPrice, maxPrice, pageable)
                .map(productMapper::toDto);

        return new PagedResponse<>(
                productPage.getContent(),
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getSize(),
                productPage.getNumber()
        );
    }

    @Override
    public List<OptionResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(customer -> new OptionResponse(customer.getId(), customer.getName()))
                .toList();
    }

    @Override
    public ProductDto findByIdProduct(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toMinimalDto)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        Supplier supplier = supplierRepository.findById(productDto.idSupplier())
                .orElseThrow(SupplierNotFoundException::new);

        Set<Category> categories = productDto.idsCategories().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(CategoryNotFoundException::new))
                .collect(Collectors.toSet());

        Product product = productMapper.toEntity(productDto);
        product.setSupplier(supplier);
        product.setCategories(categories);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        Supplier supplier = supplierRepository.findById(productDto.idSupplier())
                .orElseThrow(SupplierNotFoundException::new);

        Set<Category> categories = productDto.idsCategories().stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(CategoryNotFoundException::new))
                .collect(Collectors.toSet());

        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setStock(productDto.stock());
        product.setImgUrl(productDto.imgUrl());
        product.setSupplier(supplier);
        product.setCategories(categories);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public Boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return productReportGenerate.exportToPdf(productRepository.findAll()
                .stream().map(productMapper::toDto).toList());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return productReportGenerate.exportToXls(productRepository.findAll()
                .stream().map(productMapper::toDto).toList());
    }
}
