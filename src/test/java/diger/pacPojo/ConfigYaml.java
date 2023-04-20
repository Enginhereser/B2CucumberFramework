package diger.pacPojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.devtools.v107.browser.Browser;

@Getter
@Setter
@ToString
public class ConfigYaml {

    private readers.yaml.ConfigYaml.Application application;
    private readers.yaml.ConfigYaml.User user1;
    private readers.yaml.ConfigYaml.User user2;
    private Browser chrome;
    private Browser edge;




}


