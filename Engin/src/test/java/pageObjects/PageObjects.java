package pageObjects;

import driver.DriverE;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageObjects {


    public PageObjects(){
        PageFactory.initElements(DriverE.getDriver(), this);
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

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    public WebElement eAlertSuccess;
    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    public WebElement columnRightLogoutLink;
    @FindBy(xpath = "//div[contains(text(), 'Warning')]")
    public WebElement loginFormWarningMessage;

    @FindBy(xpath = "//button[@data-original-title='Remove']")
    public WebElement lRemoveButton;
    @FindBy(xpath = "//div[@id='top-links']//a[contains(.,'Shopping Cart')]")
    public WebElement menuShopingCart;

    @FindBy(xpath = "//div[@class='product-thumb']")
    public List<WebElement> lListedProducts;

    @FindBy(xpath = "//div[@id='cart']//button[not(@title)]")
    public WebElement lCartButton;

    @FindBy(css = ".dropdown-menu table")
    public WebElement lCartFirstTable;
    @FindBy(xpath = "//div[@class='table-responsive']//tbody/tr")
    public List<WebElement> lShopingCartProducts;

    @FindBy(xpath = "//li[@class='dropdown']/a[text()='Desktops']")
    public WebElement lDesktopsMenu;

    @FindBy(xpath = "//li[@class='dropdown']//a[text()='Show All Desktops']")
    public WebElement lDesktopsSubMenu;

    @FindBy(xpath = "//ul[@class='list-inline']/li/a")
    public List<WebElement> lTopMenu;

    @FindBy(xpath = "//button[@data-original-title='Add to Cart']")
    public List<WebElement> lWishListAddToCart;



}
