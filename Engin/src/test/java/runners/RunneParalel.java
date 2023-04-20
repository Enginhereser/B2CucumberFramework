package runners;


import driver.BrowsersE;
import driver.DriverE;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = {"src/test/resources/features/WishList.feature"},
        glue = {"stepdefs", "Hooks"},
        plugin = {"pretty",
                "json:test-output/cucumber-reports/cucumber.json",
                "html:test-output/cucumber-reports/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}

)
public class RunneParalel extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters("browser")
    public void beforTest(@Optional("chrome") String browser) {
        DriverE.getDriver(BrowsersE.valueOf(browser));
    }

}
