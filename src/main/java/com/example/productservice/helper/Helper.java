package com.example.productservice.helper;

import com.example.productservice.Entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class Helper
{
    public static String[] HEADERS={
            "id",
            "name",
            "created",
            "updated",
            "status"
    };

    public static String SHEET_NAME = "product_data";

    public static ByteArrayInputStream dataToExcel(List<Product> list) throws IOException {
        Workbook workbook = null;
        try {
            //create workbook
            workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //create sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            //create row : header row
            Row row = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }
            //value rows
            int rowIndex = 1;
            for (Product p : list) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(p.getId());
                dataRow.createCell(1).setCellValue(p.getName());
                dataRow.createCell(2).setCellValue(p.getPrice());
                dataRow.createCell(3).setCellValue(p.getCreated());
                dataRow.createCell(4).setCellValue(p.getUpdated());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e)
        {
            e.printStackTrace();
            out.println("failed to import data in excel");
            return null;
        }
        finally {

            workbook.close();
            out.close();
        }
    }
}
