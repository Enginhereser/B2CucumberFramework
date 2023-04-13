package readers.json.Engin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.FileReader;

public class TestMapperE {

    @Test
    public void testConfig1() throws Exception {

        String file="src/test/resources/datafiles/Config1.json";
        ObjectMapper mapper=new ObjectMapper();
        Config1Pojo1 config1Pojo = mapper.readValue(new FileReader(file), Config1Pojo1.class);
        System.out.println(config1Pojo.getUrl());

    }
}
