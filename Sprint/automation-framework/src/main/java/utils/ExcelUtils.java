package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {

    public static Object[][] getSheetData(
            String sheetName
    ) {

        String filePath =
                "src/test/resources/testdata/TestData.xlsx";

        try {

            FileInputStream fis =
                    new FileInputStream(filePath);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheet(sheetName);

            if (sheet == null) {

                throw new RuntimeException(
                        "Sheet not found: "
                                + sheetName
                );
            }

            int rows =
                    sheet.getPhysicalNumberOfRows();

            int cols =
                    sheet.getRow(0)
                            .getPhysicalNumberOfCells();

            Object[][] data =
                    new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {

                Row row = sheet.getRow(i);

                for (int j = 0; j < cols; j++) {

                    Cell cell = row.getCell(j);

                    data[i - 1][j] =
                            (cell == null)
                                    ? ""
                                    : cell.toString();
                }
            }

            workbook.close();
            fis.close();

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to read Excel sheet: "
                            + sheetName,
                    e
            );
        }
    }
}