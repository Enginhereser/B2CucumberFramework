package pageObjects;

import driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjects {


    public PageObjects(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@id='top-links']//a[contains(.,'My Account')]")
    public WebElement menuMyAccountLink;

    @FindBy(xpath = "//div[@id='top-links']//a[contains(.,'Login')]")
    public WebElement menuLoginLink;

    @FindBy(id = "input-email")
    public WebElement loginFormUsername;

    @FindBy(id = "input-password")
    public WebElement loginFormPassword;

    @FindBy(xpath = "//input[@value='Login']")
    public WebElement loginFormSubmitButton;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[.='Account']")
    public WebElement lSiteMapAccout;

    @FindBy(css = "#search input")
    public WebElement eSearchBoxTop;

    @FindBy(css = "#search button")
    public WebElement eSearchButtonTop;

    @FindBy(css = "#product-search")
    public WebElement eSearchContainer;

    @FindBy(css = "div.alert.alert-success")
    public WebElement eAlertSuccess;
}
