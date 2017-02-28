/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attandance.standalone.utils;

import attandance.standalone.bean.AttandanceRecord;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author huuuuxin
 */
public class ExcelUtils {

    public static List<AttandanceRecord> readDataFromExcel(String fileName) {
        List<AttandanceRecord> result = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(fileName));
            //Get the workbook instance for XLS file 
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            int lineIndex = 1;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                int columnIndex = 1;
                AttandanceRecord lineBean = new AttandanceRecord();
                //skip header
                if(lineIndex == 1) {
                    lineIndex += 1;
                    continue;
                }
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String cellValue = "";
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            cellValue =  ""+cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            cellValue =  ""+cell.getNumericCellValue();
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        default :
                            cellValue = cell.getStringCellValue();
                    }
                    lineBean.setAttribute(columnIndex , cellValue);
                    columnIndex = columnIndex + 1;
                }
                result.add(lineBean);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
