package readers.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReadWriteAppend {

    @Test
    public void readExcel() throws IOException {
        // okunacak excel dosyasi
        String file = "src/test/resources/datafiles/ExcelA.xlsx";

        // javanin dosyayi okumasi icin
        FileInputStream fileInputStream = new FileInputStream(file);

        // okunan dosya excel sayfasi olarak belirlendi
        Workbook workbook = WorkbookFactory.create(fileInputStream);

        // Okunacak sayfa belirlendi
        Sheet sheet = workbook.getSheetAt(0);   // ilk sayfa acilir
        //Sheet sheet = workbook.getSheet("Sheet1");  // "Sheet1" sayfasi acilir


        // sheet icindeki olusturulmus row sayisini verir
        int lastRow = sheet.getPhysicalNumberOfRows();

        // sheet icindeki row okunur
        Row row = sheet.getRow(0);

        // row icindeki hücre sayisini verir
        int lastCell = row.getPhysicalNumberOfCells();

        // row icindeki cell okunur
        Cell cell = row.getCell(0);
        System.out.println(cell);

        cell = row.getCell(1);
        System.out.println(cell);

        cell = row.getCell(2);
        System.out.println(cell);

        workbook.close();
        fileInputStream.close();

    }


    @Test
    public void writeNewExcelFile() throws IOException {
        // ram de bir excel workbook olusturuldu
        XSSFWorkbook workbook = new XSSFWorkbook();     // xlsx icin
        //HSSFWorkbook workbook1 = new HSSFWorkbook();    // xls icin

        // workbook'da bir sheet acildi
        XSSFSheet sheet = workbook.createSheet("sayfam");
        //HSSFSheet sheet1 = workbook1.createSheet("sayfam");

        // sheet icine ilk (index = 0) row olusturuldu
        Row row = sheet.createRow(0);

        // row icine ilk (index=0) cell olusturuldu
        Cell cell = row.createCell(0);

        // cell'e deger atandi
        cell.setCellValue("Guidersoft");

        // olusturulacak excel dosyasinin yolu ve adi, xlsx olarak
        String file = "src/test/resources/datafiles/ExcelNew.xlsx";

        // olusturulan workbook'un diske java ile yazdirilmasi icin
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // apache poi, ramde olusturulan xlsx dosyasini java araciligi ile diske yazdirir
        workbook.write(fileOutputStream);

        // workbook close edilir
        workbook.close();

        // fileoutputstream close edilir
        fileOutputStream.close();
    }



    @Test
    public void writeNewExcelFile1() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();     // xlsx icin

        XSSFSheet sheet = workbook.createSheet("sayfam");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Adi");

        cell = row.createCell(1);
        cell.setCellValue(":");

        cell = row.createCell(2);
        cell.setCellValue("Guider");

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("Soyadi");
        row1.createCell(1).setCellValue(":");
        row1.createCell(2).setCellValue("Soft");

        String file = "src/test/resources/datafiles/ExcelNewAdiSoyadi.xlsx";

        FileOutputStream fileOutputStream = new FileOutputStream(file);

        workbook.write(fileOutputStream);

        workbook.close();

        fileOutputStream.close();
    }


    @Test
    public void addDataToExcelFile() throws IOException {
        // okunacak excel dosyasi
        String file = "src/test/resources/datafiles/ExcelB.xlsx";

        // javanin dosyayi okumasi icin
        FileInputStream fileInputStream = new FileInputStream(file);

        // okunan dosya excel sayfasi olarak belirlendi
        Workbook workbook = WorkbookFactory.create(fileInputStream);

        Sheet sheet = workbook.getSheet("person");
        int lastRow = sheet.getPhysicalNumberOfRows();

        // son kisma bir satir eklendi
        Row row = sheet.createRow(lastRow);

        // eklenen satira hücreler ve degerleri yaziliyor
        row.createCell(0).setCellValue("Guider");
        row.createCell(1).setCellValue("Soft");

        // ramdeki workbook'un dosyaya yazdirilmasi icin
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        // workbook dosyaya yazdirilir
        workbook.write(fileOutputStream);

        // kapatma islemleri
        workbook.close();
        fileOutputStream.close();
        fileInputStream.close();


    }



}
