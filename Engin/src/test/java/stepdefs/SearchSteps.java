package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pageObjects.PageObjects;
import utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchSteps extends BaseSteps {

    PageObjects pageObjects = new PageObjects();

    @Given("user search for {string}")
    public void userSearchFor(String textToSearch) {
        sendkeys(pageObjects.eSearchBoxTop, textToSearch);
        click(pageObjects.eSearchButtonTop);
        waitForVisibility(pageObjects.eSearchContainer);
    }

    @When("user add {string} to the wishlist")
    public void userAddToTheWishlist(String productName) {
        click(getXpathOfButtonOfListedProduct(productName, Buttons.wish));
    }

    @Then("success notification with {string} should be visible")
    public void successNotificationWithShouldBeVisible(String text) {
        waitForVisibility(pageObjects.eAlertSuccess);
        Assert.assertTrue(pageObjects.eAlertSuccess.getText().toLowerCase().contains(text.toLowerCase()));
    }

    @When("user add {string} to the Cart")
    public void userAddToTheCart(String text) {
        WebElement webElement = pageObjects.lListedProducts.stream().filter(e -> e.getText().contains(text)).collect(Collectors.toList()).get(0);
        webElement.findElement(By.xpath(".//button[contains(.,'Add to Cart')]")).click();

    }

    @And("product {string} should be listed in Cart")
    public void productShouldBeListedInCart(String text) {
        click(pageObjects.lCartButton);
        waitForVisibility(pageObjects.lCartFirstTable);
        Assert.assertTrue(pageObjects.lCartFirstTable.getText().contains(text));
    }

    List<String> productsList = new ArrayList<>();

    @When("user add all products to the Cart")
    public void userAddProductsToTheCart() {

        pageObjects.lListedProducts.forEach(e -> {
            WebElement addToCartButton = e.findElement(By.xpath(".//button[contains(.,'Add to Cart')]"));
            scrollIntoView(addToCartButton);
            bekle(2000);
            addToCartButton.click();
            By alert = By.cssSelector("div.alert.alert-success");
            waitForVisibility(alert);
            productsList.add(e.findElement(By.cssSelector(".caption h4")).getText());

        });

    }

    @And("save products names into {string}")
    public void saveProductsNamesInto(String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));
            for (String s : productsList) {
                writer.write(s + "\n");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    List<WebElement> shopinghCartListe = new ArrayList<>();

    @Then("all listed products should be listed in Cart")
    public void allListedProductsShouldBeListedInCart() {
        click(pageObjects.menuShopingCart);
        shopinghCartListe.addAll(pageObjects.lShopingCartProducts);
        Assert.assertEquals(productsList.size(), shopinghCartListe.size());
    }

    @And("user change navigate to Cart")
    public void userChangeNavigateToCart() {
    }

    @And("user change the quantity of {string} as <quantity>")
    public void userChangeTheQuantityOfAsQuantity(String arg0) {
    }

    @Then("quantity should be updated")
    public void quantityShouldBeUpdated() {
    }

    @When("user clicks Desktops button")
    public void userClicksDesktopsButton() {
        hover(pageObjects.lDesktopsMenu);
        click(pageObjects.lDesktopsSubMenu);
    }

    @And("user clicks {string}")
    public void userClicks(String text) {
        click(asideMenu(text));
    }

    List<WebElement> wishList;
    int size;

    @When("user clicks on Add to Cart icon option in the displayed My Wish List page")
    public void userClicksOnIconOptionInTheDisplayedPage() {

        wishList = pageObjects.lWishListAddToCart;
        size=wishList.size();
        for (WebElement webElement : wishList) {
            click(webElement);
            waitForVisibility(pageObjects.eAlertSuccess);
            Utils.sleep(1000);

        }
    }


    @And("user clicks on {string} header option")
    public void userClicksOnHeaderOption(String text) {
        List<WebElement> topMenu = pageObjects.lTopMenu;
        for (int i = 0; i < topMenu.size(); i++) {
            WebElement menu = topMenu.get(i);
            if (menu.getText().contains(text)) {
                click(menu);
            }
        }

    }

    @Then("product should be successfully displayed in the Shopping Cart page")
    public void productShouldBeSuccessfullyDisplayedInThePage() {
        List<WebElement> lShopingCartProducts = pageObjects.lShopingCartProducts;
        
        Assert.assertEquals(size,lShopingCartProducts.size());

    }
}
