package readers.excel;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    String file;
    FileInputStream fileInputStream;
    Workbook workbook;
    Sheet sheet;

    @Test(description = "belirtilen xlsx dosyasindaki verileri okur")
    public void read1() throws IOException {
        file = "src/test/resources/datafiles/ExcelB.xlsx";
        fileInputStream = new FileInputStream(file);
        workbook = WorkbookFactory.create(fileInputStream);
        //sheet = workbook.getSheetAt(0);
        sheet = workbook.getSheet("person");

        int numRows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < numRows; i++) {
            Row row = sheet.getRow(i);

            int numCells = row.getPhysicalNumberOfCells();

            for (int j = 0; j < numCells; j++) {
                Cell cell = row.getCell(j);
                System.out.print(cell + "\t");
            }
            System.out.println();
        }

        workbook.close();
        fileInputStream.close();
    }


    @Test
    public void getRowValues1() {
        file = "src/test/resources/datafiles/ExcelB.xlsx";

        List<String> row2 = getRowValuesOf(file, "person", 200);

        System.out.println(row2);
        // ["Aguste", "Brayfield", "abrayfield0@purevolume.com", "Male", "359-88-3472", "Books"]
    }


    @Test
    public void getColValues1() {
        file = "src/test/resources/datafiles/ExcelB.xlsx";

        List<List<String>> data = getDataOf(file, "person");

        data.forEach(System.out::println);

    }


    public List<String> getRowValuesOf(String fileName, String page, int rowNumber) {
        try {

            List<String> myList = new ArrayList<>();

            fileInputStream = new FileInputStream(fileName);
            workbook = WorkbookFactory.create(fileInputStream);

            sheet = workbook.getSheet(page);

            int lastRow = sheet.getPhysicalNumberOfRows();

            int index = rowNumber - 1;
            if (index < 0) index = 0;
            if (index > lastRow - 1) index = lastRow - 1;

            //index = Math.min( Math.max(0, rowNumber-1), lastRow-1);

            Row row = sheet.getRow(index);
            int numCells = row.getPhysicalNumberOfCells();
            for (int j = 0; j < numCells; j++) {
                Cell cell = row.getCell(j);
                myList.add(cell.toString());
            }

            workbook.close();
            fileInputStream.close();

            return myList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<String> getColValuesOf(String fileName, String page, int colNumber) {
        try {

            List<String> myList = new ArrayList<>();
            fileInputStream = new FileInputStream(fileName);
            workbook = WorkbookFactory.create(fileInputStream);

            sheet = workbook.getSheet(page);

            int index = Math.max(0, colNumber - 1);

            int lastRow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < lastRow; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(index);
                String val = cell == null ? "" : cell.toString();
                myList.add(val);
            }
            return myList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * bu method excel sayfasindaki basliga ait verileri return eder
     *
     * @param fileName   excel dosyasi, yolu ile birlikte
     * @param page       excel sayfasi
     * @param columnName datalari return edilecek tablo casligi, index=0 olan
     * @return List
     */
    public List<String> getColValuesOf(String fileName, String page, String columnName) {
        try {

            List<String> myList = new ArrayList<>();
            fileInputStream = new FileInputStream(fileName);
            workbook = WorkbookFactory.create(fileInputStream);

            sheet = workbook.getSheet(page);
            int lastRow = sheet.getPhysicalNumberOfRows();

            Row row = sheet.getRow(0);
            int indexOfColumn = -1;
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                if (row.getCell(i).toString().equalsIgnoreCase(columnName)) {
                    indexOfColumn = i;
                    break;
                }
            }
            if (indexOfColumn < 0)
                throw new RuntimeException("column not found");

            for (int i = 1; i < lastRow; i++) {
                row = sheet.getRow(i);
                Cell cell = row.getCell(indexOfColumn);
                String val = cell == null ? "" : cell.toString();
                myList.add(val);
            }
            return myList;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    /**
     * Bu method excel safasindaki tüm verileri tablo olarak return ederr
     *
     * @param fileName excel dosya adi, path ile birlikte
     * @param page     excel sayfasi
     * @return List<List < String>>
     */
    public List<List<String>> getDataOf(String fileName, String page) {
        try {

            List<List<String>> myList = new ArrayList<>();
            fileInputStream = new FileInputStream(fileName);
            workbook = WorkbookFactory.create(fileInputStream);

            sheet = workbook.getSheet(page);
            int rowsNum = sheet.getPhysicalNumberOfRows();

            for (int i = 0; i < rowsNum; i++) {
                Row row = sheet.getRow(i);
                int cellsNum = row.getPhysicalNumberOfCells();
                List<String> list = new ArrayList<>();
                for (int j = 0; j < cellsNum; j++) {
                    list.add(row.getCell(j).toString());
                }
                myList.add(list);

            }
            return myList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
