package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@Runwith("Cucumber.class")  // -> JUnit icin
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefs"},
        tags = "@WishList",
        plugin = { "pretty",
                "json:test-output/cucumber-reports/cucumber.json",
                "html:test-output/cucumber-reports/cucumberreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)
public class RunnerLogin extends AbstractTestNGCucumberTests {


}
