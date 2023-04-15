package _tempTest;


import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import stepdefs.BaseSteps;
import utils.Utils;

import static utils.MenuActions.*;

public class TestMenu extends BaseSteps {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest(){
        driver = Driver.getDriver();
        wait = Driver.getWait();
    }

    @AfterTest
    public void afterTest(){
        Driver.quitDriver();
    }

    @Test
    public void test1(){
        driver.get("https://opencart.abstracta.us/");
        TOP_MENU.click("My Account", "Login");
        RIGHT_MENU.click("Register");
        RIGHT_MENU.click("My Account");
        RIGHT_MENU.click("Address Book");
        RIGHT_MENU.click("Downloads");
        MAIN_MENU.click("Desktops", "Mac");
        LEFT_MENU.click("Cameras");
        MAIN_MENU.click("Laptops & Notebooks", "Windows");
        MAIN_MENU.click("Components", "Monitors");
        MAIN_MENU.click("MP3 Players", "test 20");
        MAIN_MENU.click("MP3 Players", "test 12");
        MAIN_MENU.click("MP3 Players", "test 9");
        MAIN_MENU.click("Tablets");
        MAIN_MENU.click("Software");
        MAIN_MENU.click("Phones & PDAs");
        FOOTER_MENU.click("About Us");
        FOOTER_MENU.click("Contact Us");
        FOOTER_MENU.click("Newsletter");

        TOP_MENU.click("My Account", "Login");
        sendkeys(By.id("input-email"),"deneme@deneme.com");
        sendkeys(By.id("input-password"),"deneme");
        click(By.cssSelector("input[type='submit']"));
        MYACCOUNT_MENU.click("Edit Account");
        RIGHT_MENU.click("My Account");
        MYACCOUNT_MENU.click("Downloads");
        RIGHT_MENU.click("My Account");
        MYACCOUNT_MENU.click("Newsletter");
        RIGHT_MENU.click("Logout");
        Utils.sleep(3000);
    }
}
