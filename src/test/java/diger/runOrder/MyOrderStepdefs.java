package diger.runOrder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyOrderStepdefs {
    @Given("order step one")
    public void orderStepOne() {
        System.out.println("Step 1");
    }

    @When("order step two")
    public void orderStepTwo() {
        System.out.println("Step 2");
    }

    @Then("order step three")
    public void orderStepThree() {
        System.out.println("Step 3");
    }
}
