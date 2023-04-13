package readers.json.Engin.excel1;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTestler {

    String file;
    FileInputStream fileInputStream;
    Workbook workbook;
    Sheet sheet;

    @Test
    public void read() throws IOException {
        file="src/test/java/excel/ExcelB.xlsx";
        fileInputStream=new FileInputStream(file);
        workbook= WorkbookFactory.create(fileInputStream);
        sheet=workbook.getSheet("person");

        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            Row row=sheet.getRow(i);
            int cells = row.getPhysicalNumberOfCells();
            for (int i1 = 0; i1 < cells; i1++) {
                Cell cell=row.getCell(i1);
                System.out.print(cell);
            }
            System.out.println();
        }
        workbook.close();
        fileInputStream.close();
    }
    @Test
    public void getRowValue(){
        file="src/test/java/excel/ExcelB.xlsx";

    }

    public List<String> getRowValuesOf(String fileName,String page, int indexOfRow)  {
        try {
            fileInputStream=new FileInputStream(fileName);
            List<String> myList=new ArrayList<>();
            workbook= WorkbookFactory.create(fileInputStream);
            sheet=workbook.getSheet(page);

            Row rows = sheet.getRow(indexOfRow-1);
            int numCell = rows.getPhysicalNumberOfCells();
            for (int i = 0; i < numCell; i++) {
                Cell cell = rows.getCell(i);
                myList.add(cell.toString());
            }

            workbook.close();
            fileInputStream.close();
            return myList;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }
    public List<String> getColonValues(String fileName,String page, int colNumber)  {
        try {
            fileInputStream=new FileInputStream(fileName);
            List<String> myList=new ArrayList<>();

            workbook= WorkbookFactory.create(fileInputStream);
            sheet=workbook.getSheet(page);

            int lastRow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < lastRow; i++) {
                Row row=sheet.getRow(i);
                Cell cell = row.getCell(colNumber - 1);
                myList.add(cell.toString());
            }

            workbook.close();
            fileInputStream.close();
            return myList;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

}
