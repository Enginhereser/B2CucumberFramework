package readers.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.testng.annotations.Test;
import readers.MyPojo;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class TestYaml {

    @Test
    public void readYaml1() throws IOException {
        String file = "src/test/resources/datafiles/config.yaml";

        MyPojo yaml = new ConfigYaml();

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        yaml = mapper.readValue(new File(file), yaml.getClass());
        System.out.println(yaml);

    }

    @Test
    public void readYaml2() throws IOException {
        String file = "src/test/resources/datafiles/config.yaml";
        MyPojo yaml = new ConfigYaml();
        yaml = Utils.getPojo(file, yaml);
        System.out.println(((ConfigYaml)yaml).getApplication().getUrl());

    }
}
