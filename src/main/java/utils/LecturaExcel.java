package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class LecturaExcel {

    private LecturaExcel() {
    }

    public static List<Map<String, String>> leerDatosDeHojaDeExcel(String rutaDeExcel, String hojaDeExcel) throws IOException {
        ArrayList<Map<String, String>> listMaps = new ArrayList<>();
        Map<String, String> cellData = new HashMap<>();
        File file = new File(rutaDeExcel);
        try (FileInputStream inputStream = new FileInputStream(file);
             XSSFWorkbook newWorkbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet newSheet = newWorkbook.getSheet(hojaDeExcel);
            Iterator<Row> rowIterator = newSheet.iterator();
            Row titulos = rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    cell.getColumnIndex();
                    switch (cell.getCellType()) {
                        case STRING:
                            cellData.put(titulos.getCell(cell.getColumnIndex()).toString(), cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            cellData.put(titulos.getCell(cell.getColumnIndex()).toString(), String.valueOf((long) cell.getNumericCellValue()));
                            break;
                        case BLANK:
                            cellData.put(titulos.getCell(cell.getColumnIndex()).toString(), "");
                            break;
                        default:
                    }
                }
                listMaps.add(cellData);
                cellData = new HashMap<>();
            }
            return listMaps;
        }
    }

}