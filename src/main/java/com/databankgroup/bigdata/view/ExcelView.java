package com.databankgroup.bigdata.view;

import com.databankgroup.bigdata.model.Table;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public class ExcelView extends AbstractXlsxStreamingView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        //change the file name
        response.setHeader("Content-Disposition","attachment; filename=my-report-file.xlsx");

        List<Table> tableList= (List<Table>) model.get("clientlist");

        //create excel sheet

        Sheet sheet= workbook.createSheet("Data");
        sheet.setFitToPage(true);

        sheet.setDefaultColumnWidth(30);

        //create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font=workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GOLD.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setFont(font);

        //create header row
        Row header =sheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Miles A/C No");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Backconnect A/C No");
        header.getCell(2).setCellStyle(style);
        header.createCell(3).setCellValue("Product");
        header.getCell(3).setCellStyle(style);
        header.createCell(4).setCellValue("Mobile");
        header.getCell(4).setCellStyle(style);
        header.createCell(5).setCellValue("Address");
        header.getCell(5).setCellStyle(style);

        int rowCount = 1;

        for (Table table : tableList){
            Row row=sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(table.getNameInMiles());
            row.createCell(1).setCellValue(table.getMilesAccountNumber());
            row.createCell(2).setCellValue(table.getBackconnectAccountNumber());
            row.createCell(3).setCellValue(table.getPlanID());
            row.createCell(4).setCellValue(table.getMobile());
            row.createCell(5).setCellValue(table.getAddress());
        }







    }
}
