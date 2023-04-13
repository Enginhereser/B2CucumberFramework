package stepdefs;

import driver.Driver;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class BaseSteps {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BaseSteps(){
        driver = Driver.getDriver();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(100));
        wait = Driver.getWait();
    }

    public void click(By locator){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        click(element);
    }


    public void click(WebElement element){
        //wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        /*
        wait.until(driver1 -> {
            try {
                element.click();
                return true;
            }catch (Exception e1){
                return false;
            }
        });
         */

        wait.until(driver1 -> {
           try {
               element.click();
               return true;
           }catch (Exception e1){
               try {
                   new Actions(driver1).moveToElement(element).click().perform();
                   return true;
               }catch (Exception e2){
                   try {
                       ((JavascriptExecutor) driver1).executeScript("arguments[0].click()", element);
                       return true;
                   }catch (Exception e3){
                       return false;
                   }
               }
           }
        });

    }



    public void sendkeys(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        sendkeys(element, text);
    }

    public void sendkeys(WebElement element, String text){
        wait.until(driver1 -> {
            try {
                element.clear();
                element.sendKeys(text);
                return true;
            }catch (Exception e1){
                try {
                    element.clear();
                    new Actions(driver1).moveToElement(element).sendKeys(text).perform();
                    return true;
                }catch (Exception e2){
                    try {
                        element.clear();
                        ((JavascriptExecutor) driver1).executeScript("arguments[0].value='" + text + "'", element);
                        return true;
                    }catch (Exception e3){
                        return false;
                    }
                }
            }
        });

    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    public void waitForVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public By getXpathOfButtonOfListedProduct(String text, int index){
        return By.xpath("//div[@class='product-thumb' and .//div[@class='caption' and .//*[contains(.,'" + text + "')]]]//button[" + index + "]");
    }

    public By getXpathOfButtonOfListedProduct(String text, String button){
        int index = 3;
        if (button.equalsIgnoreCase("wish")) index = 2;
        if (button.equalsIgnoreCase("cart")) index = 1;

        return By.xpath("//div[@class='product-thumb' and .//div[@class='caption' and .//*[contains(.,'" + text + "')]]]//button[" + index + "]");
    }


    public By getXpathOfButtonOfListedProduct(String text, Buttons button){
        int index = button.ordinal()+1;
        return By.xpath("//div[@class='product-thumb' and .//div[@class='caption' and .//*[contains(.,'" + text + "')]]]//button[" + index + "]");
    }

    public enum Buttons {
        cart,
        wish,
        compare;
    }

    public List<String> getColValuesOf(String fileName,String page,int colNum){
        try {
            List<String> myList=new ArrayList<>();
            FileInputStream fileInputStream=new FileInputStream(fileName);
            Workbook workbook= WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();


            int index=colNum-1;
            if (index<0)index=0;
            if (index>2) index=2;


            for (int i = 0; i < rows; i++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(index);
                String val = cell == null ? "" : cell.toString();
                myList.add(val);

            }
            workbook.close();
            fileInputStream.close();
            myList.remove(0);
            return myList;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
