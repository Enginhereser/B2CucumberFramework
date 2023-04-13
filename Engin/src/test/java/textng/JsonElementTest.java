package textng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import stepdefs.BaseSteps;

import static utils.Utils.getBy;
import static utils.Utils.getValue;

public class JsonElementTest extends BaseSteps {

    @Test
    public void test1()  {
        driver.get(getValue("application", "url"));

        //By lMyAccount = By.xpath("//div[@id='top-links']//a[contains(.,'My Account')]");
        // burada lMyAccount locator'i getBy methodu ile Elements.json dosyasinda bulunan
        // topmenu altindaki myaccount'dan almaktadir
        By lMyAccount = getBy("topmenu", "myaccount");
        click(lMyAccount);
        click(getBy("topmenu","loginlink"));


        //sendkeys(locator, text);
        // getBy("login", "username") -> login altindaki username'in locator'ini return eder
        // getValue("application", "username") -> application altindaki username'i return eder
        sendkeys(getBy("login", "username"), getValue("application", "username"));
        sendkeys(getBy("login", "password"), getValue("application", "password"));

        click(getBy("login", "submitbutton"));
        waitForVisibility(getBy("account", "sitemapaccount"));

        sendkeys(getBy("search", "searchbox"), "iMac");
        click(getBy("search", "searchbutton"));
        waitForVisibility(getBy("search", "searchcontainer"));
        click(getXpathOfButtonOfListedProduct("iMac", Buttons.wish));
        waitForVisibility(getBy("search", "successalert"));

        WebElement notification = driver.findElement(getBy("search", "successalert"));
        Assert.assertTrue(notification.getText().toLowerCase().contains("wish list!"));

        driver.quit();
    }


}
