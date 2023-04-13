package utils;

import driver.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * takescreenshots
     */
    public static void takeScreenShot() {
        takeScreenShot("screenshot");
    }

    /**
     * sayfanin ekran kaydini almak icin kullanilir
     *
     * @param fileName filename of the screenshot
     */
    public static void takeScreenShot(String fileName) {
        fileName = fileName + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String filePath = "test-output/screenshots/" + fileName + ".png";
        TakesScreenshot scrShot = ((TakesScreenshot) Driver.getDriver());
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * bir elementin resmini kaydetmek icin kullanilir
     *
     * @param fileName filename of the screenshot of an element
     */
    public static void takeScreenShotOfElement(WebElement element, String fileName) {
        fileName = fileName + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
        String filePath = "test-output/screenshots/" + fileName + ".png";
        File srcFile = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * alinan screenshot'i byte[] olarak return eder
     *
     * @return screenshot in byte[]
     */
    public static byte[] getScreenShotAsByte() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    /**
     * create directory if not exist
     *
     * @param fileWithPath
     */
    public static void createDirectory(String fileWithPath) {
        String[] pathArray = fileWithPath.split("/");
        String path = "";
        if (pathArray.length > 0) {
            for (int i = 0; i < pathArray.length - 1; i++) {
                path += pathArray[i] + "/";
            }
        }
        File theDir = new File(path);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    /**
     * java sleep
     *
     * @param millis milliseconds
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * bu method test/resources/Elements.json dosyasinda tanimli elementleri bulur ve
     * locator olarak return eder
     *
     * @param main ana baslik
     * @param sub  alt baslik
     * @return By
     */
    public static By getBy(String main, String sub) {
        try {
            String jsonFile = "src/test/resources/datafiles/Elements.json";

            JSONObject object = null;
            object = (JSONObject) JSONValue.parse(new FileReader(jsonFile));

            JSONObject mainNode = (JSONObject) object.get(main);
            JSONObject subNode = (JSONObject) mainNode.get(sub);

            String type = subNode.get("type").toString();
            String locator = subNode.get("locator").toString();

            switch (type.toLowerCase()) {
                case "xpath":
                    return By.xpath(locator);
                case "css":
                    return By.cssSelector(locator);
                case "id":
                    return By.id(locator);
                case "tagname":
                    return By.tagName(locator);
                case "classname":
                    return By.className(locator);
                case "linktext":
                    return By.linkText(locator);
                case "partiallinktext":
                    return By.partialLinkText(locator);
                default:
                    return null;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String main, String key) {
        try {
            String jsonFile = "src/test/resources/datafiles/Elements.json";

            JSONObject object = null;
            object = (JSONObject) JSONValue.parse(new FileReader(jsonFile));

            JSONObject mainNode = (JSONObject) object.get(main);
            return mainNode.get(key).toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * bu method okunacak .json dosyasini pojo.class'a map eder
     * @param
     * @param
     * @return MyJsonPojo olarak return eder, islem sirasinda sub class'a cast edilmeli
     */
    /*public static MyJsonPojo getPojo(String file, MyJsonPojo pojo){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new FileReader(file), pojo.getClass());
        } catch (IOException e) {
            //return null;
            throw new RuntimeException(e);
        }
    }*/


    // özel bir excel dosyasinda yazili gherkin satirlarindan feature file olusturma.
    public static void createFeatureFileFromExcel(String excelFile, String featureFile){
        final String ENTER = "\n";
        try {
            FileWriter fileWriter = new FileWriter(featureFile);

            // java excel'i okudu
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            // Apache poi excel'i workbook olarak tanidi
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            // ilk sayfa okundu
            Sheet sheet = workbook.getSheetAt(0);

            // row sayisi alindi
            int rowNums = sheet.getPhysicalNumberOfRows();

            String featureLine = "Feature: " + sheet.getRow(1).getCell(0).toString();
            fileWriter.write(featureLine + ENTER);

            String scenarioLine = sheet.getRow(1).getCell(1).toString();
            scenarioLine += ":";
            scenarioLine += sheet.getRow(1).getCell(2).toString();

            fileWriter.write(scenarioLine + ENTER);

            for (int i = 1; i < rowNums; i++) {
                Cell cell = sheet.getRow(i).getCell(3);
                String str = cell == null ? "" : cell.toString();
                fileWriter.write(str + ENTER);
            }
            workbook.close();
            fileInputStream.close();
            fileWriter.close();




        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> getRowValuesOf(String fileName, String page, int rowNumber) {
        try {

            List<String> myList = new ArrayList<>();

            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheet(page);

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
            FileInputStream fileInputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);

            Sheet sheet = workbook.getSheet(page);
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
