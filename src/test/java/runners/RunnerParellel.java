package runners;


import driver.Browsers;
import driver.Driver;
import io.cucumber.testng.*;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.util.Objects;

// TestNG ile Cucumber'in anlasmasini saglayan CucumberOptions'dur
@CucumberOptions(
        //features = {"src/test/resources/features"},       // features altindaki tüm feature dosyalarini run eder
        //features = {"c:/features"},
        //features = {"src/test/resources/features/Login.feature"}, // Login.feature run edilir
        //features = {"src/test/resources/features/Login.feature", "src/test/resources/features/Login2.feature"},
        features = {"src/test/resources/features/WishList.feature"},
        //glue = {"stepdefs"},           // Hooks bu package da degilse calismatirilmaz
        glue = {"stepdefs", "hooks"},
        //tags = "@Wishlist", // tagi @Wishlist olan feature ya da scenariolari run eder
        plugin = {"pretty",
                "json:test-output/cucumber-reports/cucumber.json", // cucumber json report
                "html:test-output/cucumber-reports/cucumber.html", // cucumber html report
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"  // extent report plugin'i
        },    // özellikle rapor icin kullanilir
        dryRun = false   // true ise sadece stepdefs'lerin dogru tanimlanip tanimlanmaigini kontrol eder, run etmez

)
public class RunnerParellel extends AbstractTestNGCucumberTests{

        @BeforeTest
        @Parameters("browser")
        public void beforeTest(@Optional("chrome") String browser){
                Driver.getDriver(Browsers.valueOf(browser));
        }
}


/* runner class TestNG'ye aittir
        Runner'i exceutable yapmak icin "AbstractTestNGCucumberTests" class'ina extend etmeli
 */
