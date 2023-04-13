package utils;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public enum MenuActions {
    MAIN_MENU ("main", By.xpath(".//nav[@id='menu']//ul[@class='nav navbar-nav']")),
    TOP_MENU ("top", By.xpath(".//div[@id='top-links']")),
    LEFT_MENU ("left", By.cssSelector("#column-left")),
    RIGHT_MENU ("right", By.cssSelector("#column-right")),
    FOOTER_MENU ("footer", By.cssSelector("footer")),
    MYACCOUNT_MENU ("myaccount", By.cssSelector("#account-account #content"));

    private By locator;
    private String name;
    private WebDriver driver;
    private WebDriverWait wait;
    private String xpathString = ".//a[contains(., '%s')]";

    MenuActions(String name, By locator) {
        this.locator = locator;
        this.name = name;
        driver = Driver.getDriver();
        wait = Driver.getWait();
    }

    public By getLocator() {
        return locator;
    }


    public void click(String...texts){
        for (int i = 0; i < texts.length; i++) {
            By subMenulocator = By.xpath(String.format(xpathString, texts[i]));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(subMenulocator));
            if (i==0 && this.name.equalsIgnoreCase("main")){
                if (texts.length==1) {
                    element.click();
                }else {
                    new Actions(driver).moveToElement(element).perform();
                }
            }else {
                wait.until(driver1 -> {
                    WebElement subMenu;
                    try {
                        subMenu = driver1.findElement(this.locator).findElement(subMenulocator);
                        subMenu.click();
                        return true;
                    }catch (Exception e1){
                        try {
                            subMenu = driver1.findElement(this.locator).findElement(subMenulocator);
                            new Actions(driver1).moveToElement(subMenu).click().perform();
                            return true;
                        }catch (Exception e2){
                            try {
                                subMenu = driver1.findElement(this.locator).findElement(subMenulocator);
                                ((JavascriptExecutor) driver1).executeScript("arguments[0].click()", subMenu);
                                return true;
                            }catch (Exception e3){
                                return false;
                            }
                        }
                    }
                });
            }
        }
    }



}
