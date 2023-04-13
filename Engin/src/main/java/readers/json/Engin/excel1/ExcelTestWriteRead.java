package readers.json.Engin.excel1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTestWriteRead {

    @Test
    public void readEcxel() throws IOException {
        String file = "src/test/java/excel/ExcelA.xlsx";

        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet("sheet1");
        int toplamRowSayisi = sheet.getPhysicalNumberOfRows();
        //Sheet sheet1=workbook.getSheetAt(0);

        Row row = sheet.getRow(0);
        int toplamHücreSayisi = row.getPhysicalNumberOfCells();

        Cell cell = row.getCell(0);

        System.out.println(cell);
        workbook.close();
        fileInputStream.close();
    }

    @Test
    public void readExcel2() throws IOException {
        String file = "src/test/java/excel/ExcelA.xlsx";
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            int cells = row.getPhysicalNumberOfCells();
            for (int i1 = 0; i1 < cells; i1++) {
                Cell cell = row.getCell(i1);
                System.out.println(cell);
            }
            System.out.println();
        }
        workbook.close();
        fileInputStream.close();


    }

    @Test
    public void test3() throws IOException {
        String file = "src/test/java/excel/ExcelD.xlsx";
       /* System.out.println(getRowListe(file,"person",200));
        List<String> col2 = getColl(file, "person", 2);
        col2.forEach(System.out::println);
        List<String> col = getColl2(file, "person", 3);
        col.forEach(System.out::println);*/
        /*List<String> colName = getColName(file, "person", "salary");
        colName.forEach(System.out::println);
        uclercarp().forEach(System.out::println);*/
        writeExcel(file,uclercarp());
    }

    public List<String> getRowListe(String fileName, String page, int rowNummer) throws IOException {
        List<String> myList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(page);
        int lastRow = sheet.getPhysicalNumberOfRows();

        /*int indx=rowNummer-1;
        if (indx<0)indx=1;
        if (indx>lastRow)indx=lastRow-1;*/

        /*if (rowNummer<1)rowNummer=1;
        if (rowNummer>lastRow) rowNummer=lastRow-1;*/

        int index = Math.min(Math.max(0, rowNummer - 1), lastRow - 1);

        Row row = sheet.getRow(index);
        int cells = row.getPhysicalNumberOfCells();
        for (int i = 0; i < cells; i++) {
            Cell cell = row.getCell(i);
            myList.add(cell.toString());
        }
        return myList;
    }

    public List<String> getColl(String fileName, String page, int colNum) {

        try {
            List<String> myList = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(page);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                int cells = row.getPhysicalNumberOfCells();
                for (int i1 = 0; i1 < cells; i1++) {
                    if (colNum < 0) colNum = 1;
                    if (colNum > cells) colNum = cells - 1;
                    if (i1 == colNum) {
                        Cell cell = row.getCell(colNum - 1);
                        myList.add(cell.toString() + "\n");
                    }
                }
            }
            return myList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getColl2(String fileName, String page, int colNum) {

        try {
            List<String> myList = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(page);
            int index = Math.max(0, colNum - 1);
            int lastrow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < lastrow; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(index);
                myList.add(cell.toString());
            }
            return myList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getColName(String fileName, String page, String columName) {
        try {
            List<String> myList = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheet(page);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                int cells = row.getPhysicalNumberOfCells();
                for (int i1 = 0; i1 < cells; i1++) {
                    String stringCellValue = row.getCell(i).getStringCellValue();
                    int index = 0;
                    Cell cell;
                    if (stringCellValue.contentEquals(columName)) {
                        index = row.getCell(i1).getColumnIndex();
                        cell = row.getCell(index);
                        myList.add(cell.toString());
                    }

                }
                return myList;

            }
            return myList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Test
    public void writeExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sayfam");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Engin");
        String file = "src/test/java/excel/ExcelNew1.xlsx";
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();

    }

    @Test
    public void writeExcelCalc() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sayfa");
        int sabit = 1;
        int sonuc;
        List<List<String>> list=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 9; j++) {
                Cell cell = row.getCell(j);
                cell.setCellValue(list.get(i).get(j));
                /*cell.setCellValue("*");
                cell.setCellValue(3);
                sonuc=sabit*3;
                cell.setCellValue(sonuc);*/

            }
            String file = "src/test/java/excel/ExcelNew2.xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            workbook.close();
            fileOutputStream.close();
        }


    }

    public List<List<String>> uclercarp() {

        List<List<String>> myList = new ArrayList<>();
        int sayi = 3;

        for (int i = 1; i < 10; i++) {
            List<String> satir = new ArrayList<>();
            satir.add(String.valueOf(i));
            satir.add("*");
            satir.add(String.valueOf(sayi));
            satir.add("=");
            int sonuc = i * sayi;
            satir.add(String.valueOf(sonuc));
            myList.add(satir);

        }
        return myList;
    }
    public List<List<String>> writeExcel(String file,List<List<String>> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < list.get(i).size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(list.get(i).get(j));
            }

        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
        return list;
    }

    @Test
    public void addDataExcel() throws IOException {
        String file = "src/test/java/excel/ExcelA.xlsx";

        FileInputStream fileInputStream = new FileInputStream(file);

        Workbook workbook = WorkbookFactory.create(fileInputStream);

        Sheet sheet = workbook.getSheet("person");
        int lastRow = sheet.getPhysicalNumberOfRows();

        Row row = sheet.createRow(lastRow);
        row.createCell(0).setCellValue("Guider");
        row.createCell(1).setCellValue("Soft");
    }

    @Test
    public void Add() throws IOException {
        String file = "src/test/java/excel/ExcelB.xlsx";
        FileInputStream fileInputStream = new FileInputStream(file);

        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet("person");
        int lastRow = sheet.getPhysicalNumberOfRows();
        Row row = sheet.createRow(lastRow);
        row.createCell(0).setCellValue("Engin");
        row.createCell(1).setCellValue("Hereser");

        FileOutputStream fileOutputStream=new FileOutputStream(file);
        workbook.write(fileOutputStream);

        workbook.close();
        fileOutputStream.close();
        fileInputStream.close();


    }
    @Test
    public void te(){
        String file = "src/test/java/excel/ExcelB.xlsx";

        System.out.println(getColValuesOf(file, "person", 1));
    }







    public List<String> getColValuesOf(String fileName, String page, int colNumber) {
        try {

            List<String> myList = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheet(page);

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
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheet(page);
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



}
