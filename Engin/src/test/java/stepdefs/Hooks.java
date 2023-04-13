package stepdefs;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import readers.property.PropertyReader;



public class Hooks {

    @AfterStep
    public void after1(Scenario scenario){

        boolean scrShot = PropertyReader.read().get("takescreenshot").equalsIgnoreCase("true");
        String takeScrOn = PropertyReader.read().get("takescreenshot.on");
        if (scrShot) {
            if (takeScrOn.equalsIgnoreCase("fail")){
                if (scenario.isFailed()) {
                    byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                }
            }else{
                byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
        }



    }


    @After(order = 0)
    public void after0(){

        Driver.quitDriver();
    }
}


