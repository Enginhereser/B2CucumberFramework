package readers.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExcelReader {

    private String filePath;
    private String sheetName;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private boolean append = false;
    private File file;
    private FileInputStream inputStream;
    private FileOutputStream outputStream;

    /**
     * constructor, sayfa adi Sheet1 olarak alinir
     * @param filePath dosya yolu, adi ile birlikte
     */
    public ExcelReader(String filePath){
        this(filePath, 0);
    }


    /**
     * constructor, with pageIndex
     * @param filePath dosya yolu, adi ile birlikte
     * @param sheetNumber sayfa indexi
     */
    public ExcelReader(String filePath, int sheetNumber){
        this.filePath = filePath;
        if (Utils.isFileExist(filePath)){
            append = true;
            read("", sheetNumber);
        }else {
            create("Sayfa1");
        }
    }

    /**
     * constructor
     * @param filePath dosya yolu, adi ile birlikte
     * @param sheetName sayfa adi
     */
    public ExcelReader(String filePath, String sheetName){
        this.filePath = filePath;
        this.sheetName = sheetName;
        if (Utils.isFileExist(filePath)){
            append = true;
            read(sheetName, Integer.MIN_VALUE);
        }else {
            create(sheetName);
        }

    }

    /**
     * excel sayfasi olusturur
     */
    private void create(String sheetName){
        Utils.createDirectory(filePath);
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    /**
     * excel dosyasini okur
     */
    private void read(String sheetName, int sheetNum){
        file = new File(filePath);
        try {
            inputStream = new FileInputStream(filePath);
            workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
            int totalSheetNumber = workbook.getNumberOfSheets();
            int sheetIndex = Math.min(Math.max(0, sheetNum-1), totalSheetNumber);
            if (sheetNum != Integer.MIN_VALUE)
                sheet = workbook.getSheetAt(sheetIndex);
            else
                sheet = workbook.getSheet(sheetName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * excel dosyasini yazar ve kapatir
     */
    public void close(){
        try {
            outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * excel sayfa basliklarini yazar
     * @param headers header as varargs
     */
    public void setHeader(String...headers){
        setHeader(Arrays.asList(headers));
    }

    /**
     * excel sayfa basliklarini yazar
     * @param headers header as list
     */
    public void setHeader(List<Object> headers){
        if (!append) {
            Row row = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue((String) headers.get(i));
            }
        }

    }

    /**
     * excel sayfasina veri yazar
     * @param values as varargs
     */
    public void writeData(String...values){
        int rowNum = sheet.getPhysicalNumberOfRows();
        Row row = sheet.createRow(rowNum++);
        for (int i = 0; i < values.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(values[i]);
        }
    }

    /**
     * excel sayfasina veri yazar
     * @param rows as list
     */
    public void writeData(List<List<Object>> rows){
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows.size(); i++) {
            Row row = sheet.createRow(rowNum++);
            for (int j = 0; j < rows.get(i).size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue((String) rows.get(i).get(j));
            }
        }
    }


    /**
     * excel sayfasindaki tüm verileri return eder
     * @return as list
     */

    public List<List<Object>> getData() {
        return getData(0);
    }

    /**
     * excel sayfasindaki tüm verileri return eder
     * @param startRow baslama satiri
     * @return list
     */
    public List<List<Object>> getData(int startRow) {
        List<List<Object>> lists = new LinkedList<>();

        int rowCount = sheet.getPhysicalNumberOfRows();

        for(int i = startRow; i < rowCount; i++) {
            List<Object> rowList = new LinkedList<>();
            Row row = sheet.getRow(i);

            int cellCount = row.getPhysicalNumberOfCells();

            for(int j = 0; j < cellCount; j++) {
                rowList.add(row.getCell(j).toString());
            }
            lists.add(rowList);
        }
        return lists;
    }


    /**
     * tablo basligini return eder
     * @return header as list
     */
    public List<Object> getHeader() {
        List<Object> list = new LinkedList<>();

        sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(0);

        int cellCount = row.getPhysicalNumberOfCells();

        for(int i = 0; i < cellCount; i++) {
            list.add(row.getCell(i).toString());
        }
        return list;
    }

    /**
     * belirli bir satir verilerini return eder
     * @param rowNumber row number
     * @return as list
     */
    public List<Object> getRow(int rowNumber) {
        List<Object> list = new LinkedList<>();

        sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(rowNumber);

        int cellCount = row.getPhysicalNumberOfCells();

        for(int i = 0; i < cellCount; i++) {
            list.add(row.getCell(i).toString());
        }
        return list;
    }

    /**
     * Exeldeki belirtilen tüm sütun degerlerini list olarak return eder
     * @param colNum column numarasi
     * @return list
     */
    public List<Object> getColumn(int colNum) {
        return getColumn(colNum, 0);
    }

    /**
     * Exeldeki belirtilen sütun degerlerini list olarak return eder
     * @param colNum verisi istenilen column numarasi
     * @param startRow baslama satir numarasi
     * @return list
     */
    public List<Object> getColumn(int colNum, int startRow) {
        List<Object> list = new LinkedList<>();

        int rowCount = sheet.getPhysicalNumberOfRows();
        colNum = Math.min(1, colNum)-1;
        for(int i = startRow; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            list.add(row.getCell(colNum));
        }
        return list;
    }

    /**
     * Bu method exeldeki belirtilen sütun degerlerini list olarak return eder
     * @param colName verisi istenilen column adi
     * @return list
     */
    public List<Object> getColumn(String colName, int startFromRow) {
        List<Object> list = new LinkedList<>();
        int requiredColNum = 0;
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colNumExcel = sheet.getRow(0).getPhysicalNumberOfCells();
        Row row0 = sheet.getRow(0);
        for (int i = 0; i < colNumExcel; i++) {
            if (row0.getCell(i).toString().equalsIgnoreCase(colName)){
                requiredColNum = i;
                break;
            }
        }
        for(int i = startFromRow; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            list.add(row.getCell(requiredColNum).toString());
        }
        return list;
    }
}
