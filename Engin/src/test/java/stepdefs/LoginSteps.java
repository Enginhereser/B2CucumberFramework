package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.PageObjects;
import readers.property.PropertyReader;

import java.util.List;
import java.util.Map;

public class LoginSteps extends BaseSteps{

    PageObjects pageObjects;
    @Given("user on homepage")
    public void userOnHomepage() {
        pageObjects = new PageObjects();
        String url = PropertyReader.read().get("url");
        driver.get(url);

    }

    @When("user clicks My Account Link")
    public void userClicksMyAccountLink() {
        click(pageObjects.menuMyAccountLink);
    }

    @And("user clicks Login Link")
    public void userClicksLoginLink() {
        click(pageObjects.menuLoginLink);
    }

    @Then("Login page should be visible")
    public void loginPageShouldBeVisible() {
        waitForVisibility(pageObjects.loginFormUsername);
    }

    @When("user fill the login form with the following data")
    public void userFillTheLoginFormWithTheFollowingData(DataTable table) {
        Map<String, String> map = table.asMap();
        sendkeys(pageObjects.loginFormUsername, map.get("username"));
        sendkeys(pageObjects.loginFormPassword, map.get("password"));
        Assert.fail();
    }

    @And("user clicks Login button")
    public void userClicksLoginButton() {
        click(pageObjects.loginFormSubmitButton);
    }

    @Then("login should be successfull")
    public void loginShouldBeSuccessfull() {
        waitForVisibility(pageObjects.lSiteMapAccout);
    }

    @When("user clicks the following links with text")
    public void userClicksTheFollowingLinksWithText(DataTable table) {
        String xpathOfLink = "//a[contains(., '%s')]";
        List<String> list = table.asList();
        for (String text : list) {
            By locator = By.xpath(String.format(xpathOfLink, text));
            click(locator);
        }

    }

    @Then("login should be {string}")
    public void loginShouldBe(String arg0) {

    }

    @When("user login with username {string} and password {string}")
    public void userLoginWithUsernameAndPassword(String username, String password) {
        click(pageObjects.menuMyAccountLink);
        click(pageObjects.menuLoginLink);
        waitForVisibility(pageObjects.loginFormUsername);
        sendkeys(pageObjects.loginFormUsername, username);
        sendkeys(pageObjects.loginFormPassword, password);
        click(pageObjects.loginFormSubmitButton);

    }
    List<String> userName;
    List<String> password;
    List<String> login;

    @When("user try to login with credential given in excel file name as {string}")
    public void userTryToLoginWithCredentialGivenInExcelFileNameAs(String fileName) {
        String file="src/test/resources/datafiles/"+fileName;
        click(pageObjects.menuMyAccountLink);
        click(pageObjects.menuLoginLink);
        userName= getColValuesOf(file, "Sheet1", 1);
       password = getColValuesOf(file, "Sheet1", 2);
        login= getColValuesOf(file, "Sheet1", 3);

        for (int i = 0; i < userName.size(); i++) {
            if (login.get(i).equalsIgnoreCase("true")) {
                sendkeys(pageObjects.loginFormUsername, userName.get(i));
                sendkeys(pageObjects.loginFormPassword, password.get(i));
                click(pageObjects.loginFormSubmitButton);
                waitForVisibility(pageObjects.lSiteMapAccout);
                click(pageObjects.columnRightLogoutLink);
                driver.navigate().to("https://opencart.abstracta.us/index.php?route=account/login");
            }else {
                sendkeys(pageObjects.loginFormUsername, userName.get(i));
                sendkeys(pageObjects.loginFormPassword, password.get(i));
                click(pageObjects.loginFormSubmitButton);
                waitForVisibility(pageObjects.loginFormWarningMessage);
            }
        }
    }

    @Then("login should be as in excel file")
    public void loginShouldBeAsInExcelFile() {
        


    }
    List<WebElement> elements;
    @When("Clean the Cart")
    public void cleanTheCart() {
        click(pageObjects.menuShopingCart);
        elements= driver.findElements(By.xpath("//button[@data-original-title='Remove']"));
        while (elements.size()>0){
            int size=elements.size();
            click(pageObjects.lRemoveButton);
            By loc=By.xpath("//button[@data-original-title='Remove']");
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(loc,size));
            elements = driver.findElements(By.xpath("//button[@data-original-title='Remove']"));
        }


    }

    @Then("Cart should be empty")
    public void cartShouldBeEmpty() {
        elements= driver.findElements(By.xpath("//button[@data-original-title='Remove']"));
        Assert.assertEquals(elements.size(),0);
        wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }
}
