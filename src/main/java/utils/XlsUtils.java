package utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.nio.file.Files;

public class XlsUtils {

    private FileInputStream inputStream;
    private FileOutputStream outputStream;
    private XSSFWorkbook workbook;
    private HSSFWorkbook workbookF2;
    private XSSFSheet sheet;
    private HSSFSheet sheetF2;
    private XSSFRow row;
    private HSSFRow rowF2;
    private XSSFCell cell;
    private HSSFCell cellF2;
    private CellStyle style;
    private final String path;
    private CellStyle cellStyle;

    public XlsUtils(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        this.sheet = this.workbook.getSheet(sheetName);
        int rowcount = this.sheet.getLastRowNum();
        this.workbook.close();
        this.inputStream.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        this.sheet = this.workbook.getSheet(sheetName);
        this.row = this.sheet.getRow(rownum);
        int cellcount = this.row.getLastCellNum();
        this.workbook.close();
        this.inputStream.close();
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        this.sheet = this.workbook.getSheet(sheetName);
        this.row = this.sheet.getRow(rownum);
        this.cell = this.row.getCell(colnum);
        DataFormatter formatter = new DataFormatter();

        String data;
        try {
            data = formatter.formatCellValue(this.cell);
        } catch (Exception var7) {
            data = "";
        }

        this.workbook.close();
        this.inputStream.close();
        return data;
    }

    public String getLastCell(String sheetName, int colnum) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbookF2 = new HSSFWorkbook(this.inputStream);
        this.sheetF2 = this.workbookF2.getSheet(sheetName);
        this.rowF2 = this.sheetF2.getRow(this.sheetF2.getLastRowNum());
        this.cellF2 = this.rowF2.getCell(colnum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(this.cellF2);
        } catch (Exception e) {
            data = "";
        }
        this.workbookF2.close();
        this.inputStream.close();
        return data;
    }

    public String getValorDiarioRecaudo() throws IOException {
        String data = null;
        File file = new File(this.path);
        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(file.toPath()));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.replace("\t", "").replace("\n", "").replace("\0", "");
            if (line.contains("Por valor de: ")) {
                String[] aux = line.split(": ");
                data = aux[aux.length - 1];
            }
        }
        bufferedReader.close();
        return data;
    }

    public String obtenerFormula(String sheetName, int row, int column) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        this.sheet = this.workbook.getSheet(sheetName);
        this.row = this.sheet.getRow(row);
        this.cell = this.row.getCell(column);
        String formula = this.cell.getCellFormula();
        this.outputStream = new FileOutputStream(this.path);
        this.workbook.write(this.outputStream);
        this.workbook.close();
        this.inputStream.close();
        this.outputStream.close();
        return formula;
    }

    public void agregarFormula(String sheetName, int row, int column, String formula) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        if (this.workbook.getSheetIndex(sheetName) == -1) {
            this.workbook.createSheet(sheetName);
        }
        this.sheet = this.workbook.getSheet(sheetName);
        if (this.sheet.getRow(row) == null) {
            this.sheet.createRow(row);
        }
        cellStyle = workbook.createCellStyle();
        this.row = this.sheet.getRow(row);
        this.cell = this.row.createCell(column);
        this.cell.setCellFormula(formula);
        cellStyle.setDataFormat((short) 7);
        this.cell.setCellStyle(cellStyle);
        this.outputStream = new FileOutputStream(this.path);
        this.workbook.write(this.outputStream);
        this.workbook.close();
        this.inputStream.close();
        this.outputStream.close();
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(this.path);
        if (!xlfile.exists()) {
            this.workbook = new XSSFWorkbook();
            this.outputStream = new FileOutputStream(this.path);
            this.workbook.write(this.outputStream);
        }
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        if (this.workbook.getSheetIndex(sheetName) == -1) {
            this.workbook.createSheet(sheetName);
        }
        this.sheet = this.workbook.getSheet(sheetName);
        if (this.sheet.getRow(rownum) == null) {
            this.sheet.createRow(rownum);
        }
        cellStyle = workbook.createCellStyle();
        this.row = this.sheet.getRow(rownum);
        this.cell = this.row.createCell(colnum);
        this.cell.setCellValue(data);
        cellStyle.setDataFormat((short) 0);
        this.cell.setCellStyle(cellStyle);
        this.outputStream = new FileOutputStream(this.path);
        this.workbook.write(this.outputStream);
        this.workbook.close();
        this.inputStream.close();
        this.outputStream.close();
    }

    public void setCellData(String sheetName, int rownum, int colnum, double data, int formato) throws IOException {
        File xlfile = new File(this.path);
        if (!xlfile.exists()) {
            this.workbook = new XSSFWorkbook();
            this.outputStream = new FileOutputStream(this.path);
            this.workbook.write(this.outputStream);
        }
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        if (this.workbook.getSheetIndex(sheetName) == -1) {
            this.workbook.createSheet(sheetName);
        }
        this.sheet = this.workbook.getSheet(sheetName);
        if (this.sheet.getRow(rownum) == null) {
            this.sheet.createRow(rownum);
        }
        cellStyle = workbook.createCellStyle();
        this.row = this.sheet.getRow(rownum);
        this.cell = this.row.createCell(colnum);
        this.cell.setCellValue(data);
        cellStyle.setDataFormat((short) formato);
        this.cell.setCellStyle(cellStyle);
        this.outputStream = new FileOutputStream(this.path);
        this.workbook.write(this.outputStream);
        this.workbook.close();
        this.inputStream.close();
        this.outputStream.close();
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        this.sheet = this.workbook.getSheet(sheetName);
        this.row = this.sheet.getRow(rownum);
        this.cell = this.row.getCell(colnum);
        this.style = this.workbook.createCellStyle();
        this.style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        this.style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        this.cell.setCellStyle(this.style);
        this.workbook.write(this.outputStream);
        this.workbook.close();
        this.inputStream.close();
        this.outputStream.close();
    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        this.inputStream = new FileInputStream(this.path);
        this.workbook = new XSSFWorkbook(this.inputStream);
        this.sheet = this.workbook.getSheet(sheetName);
        this.row = this.sheet.getRow(rownum);
        this.cell = this.row.getCell(colnum);
        this.style = this.workbook.createCellStyle();
        this.style.setFillForegroundColor(IndexedColors.RED.getIndex());
        this.style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        this.cell.setCellStyle(this.style);
        this.workbook.write(this.outputStream);
        this.workbook.close();
        this.inputStream.close();
        this.outputStream.close();
    }
}
