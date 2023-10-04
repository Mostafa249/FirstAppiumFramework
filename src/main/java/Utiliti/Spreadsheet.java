package Utiliti;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Spreadsheet {
    private static File file;
    private static XSSFSheet sheet;

    public static String getData(String fileName, String sheetName, int RN, int CN) throws IOException {
        file = new File(System.getProperty("user.dir") + "/src/resources/TestData/" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook WB = new XSSFWorkbook(inputStream);
        sheet = WB.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        String field = formatter.formatCellValue(sheet.getRow(RN).getCell(CN));
        return field;
    }
}