package com.example.appsalesback.util.mapper;

import com.example.appsalesback.persistence.entity.Invoice;
import com.example.appsalesback.presentation.dto.InvoiceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceDto toDto(Invoice invoice);
    Invoice toEntity(InvoiceDto invoiceDto);
}
