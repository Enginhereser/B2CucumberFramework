package diger.runOrder;


import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/java/diger/runOrder/MyFeature.feature"},
        glue = {"diger/runOrder"}
)
public class MyRunnerForOrder extends BaseRunner {


}
