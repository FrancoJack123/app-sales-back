package com.example.appsalesback.presentation.controller;

import com.example.appsalesback.presentation.dto.SupplierDto;
import com.example.appsalesback.presentation.response.OptionResponse;
import com.example.appsalesback.presentation.response.PagedResponse;
import com.example.appsalesback.service.interfaces.SupplierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_PAGE;
import static com.example.appsalesback.util.constants.PaginationConstants.DEFAULT_SIZE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
@Tag(name = "Suppliers", description = "Endpoints for managing suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/paginated")
    public PagedResponse<SupplierDto> findAllWithPagination(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String ruc,
            @RequestParam(defaultValue = DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = DEFAULT_SIZE) Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return supplierService.findAllSuppliersWithPagination(name, ruc, pageable);
    }

    @GetMapping()
    public List<OptionResponse> findAll() {
        return supplierService.findAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierDto findById(@PathVariable Long id) {
        return supplierService.findByIdSupplier(id);
    }

    @GetMapping("/exists/ruc/{ruc}")
    public Boolean existsByRuc(@PathVariable String ruc) {
        return supplierService.existsByRucSupplier(ruc);
    }

    @PostMapping()
    public SupplierDto save(@Valid @RequestBody SupplierDto supplierDto) {
        return supplierService.saveSupplier(supplierDto);
    }

    @PutMapping("/{id}")
    public SupplierDto update(@PathVariable Long id, @Valid @RequestBody SupplierDto supplierDto) {
        return supplierService.updateSupplier(id, supplierDto);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id) {
        return supplierService.deleteSupplier(id);
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("supplierReport", "supplierReport.pdf");
        return ResponseEntity.ok().headers(headers).body(supplierService.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("supplierReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(supplierService.exportXls());
    }
}
