package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import driver.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import readers.json.MyJsonPojo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * @param file okunacak json file
     * @param pojo parent'i  MyJsonPojo  olan pojo class'i
     * @return MyJsonPojo olarak return eder, islem sirasinda sub class'a cast edilmeli
     */
    public static MyJsonPojo getPojo(String file, MyJsonPojo pojo){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new FileReader(file), pojo.getClass());
        } catch (IOException e) {
            //return null;
            throw new RuntimeException(e);
        }
    }


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

}
