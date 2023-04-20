package _tempTest;

import org.testng.annotations.Test;
import stepdefs.BaseSteps;

public class TGest1 extends BaseSteps {


    @Test
    public void test(){
        driver.get("http://opencart.abstracta.us/");
        getMenuElements().forEach(e->{
            hover(e);
        });
    }
}
