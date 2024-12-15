package com.example.appsalesback.util.reportGenerator;

import com.example.appsalesback.presentation.dto.ProductDto;
import com.example.appsalesback.presentation.dto.SupplierDto;
import com.example.appsalesback.util.reportGenerator.template.ProductReport;
import com.example.appsalesback.util.reportGenerator.template.SupplierReport;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierReportGenerate {
    public byte[] exportToPdf(List<SupplierDto> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    public byte[] exportToXls(List<SupplierDto> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<SupplierDto> list) throws FileNotFoundException, JRException {
        List<SupplierReport> supplierReportList = getSupplierReports(list);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("suppliersDataSet", new JRBeanCollectionDataSource(supplierReportList));

        return JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:supplierReport.jrxml")
                        .getAbsolutePath()), parameters, new JREmptyDataSource());
    }

    private static List<SupplierReport> getSupplierReports(List<SupplierDto> list) {
        List<SupplierReport> supplierReportList = new ArrayList<>();

        for (SupplierDto supplierDto : list) {
            SupplierReport supplierReport = new SupplierReport();
            supplierReport.setId(supplierDto.id());
            supplierReport.setName(supplierDto.name());
            supplierReport.setRuc(supplierDto.ruc());
            supplierReport.setContactInfo(supplierDto.contactInfo());
            supplierReportList.add(supplierReport);
        }

        return supplierReportList;
    }

}
