package com.example.appsalesback.util.reportGenerator;

import com.example.appsalesback.presentation.dto.ProductDto;
import com.example.appsalesback.util.reportGenerator.template.ProductReport;
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
public class ProductReportGenerate {
    public byte[] exportToPdf(List<ProductDto> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    public byte[] exportToXls(List<ProductDto> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    private JasperPrint getReport(List<ProductDto> list) throws FileNotFoundException, JRException {
        List<ProductReport> productReportList = getProductReports(list);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("productsDataSet", new JRBeanCollectionDataSource(productReportList));

        return JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:productReport.jrxml")
                        .getAbsolutePath()), parameters, new JREmptyDataSource());
    }

    private static List<ProductReport> getProductReports(List<ProductDto> list) {
        List<ProductReport> productReportList = new ArrayList<>();

        for (ProductDto productDto : list) {
            ProductReport productReport = new ProductReport();
            productReport.setId(productDto.id());
            productReport.setName(productDto.name());
            productReport.setDescription(productDto.description());
            productReport.setStock(productDto.stock());
            productReport.setPrice(productDto.price());
            productReportList.add(productReport);
        }

        return productReportList;
    }
}
