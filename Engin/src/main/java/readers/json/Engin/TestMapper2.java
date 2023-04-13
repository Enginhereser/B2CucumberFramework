package readers.json.Engin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class TestMapper2 {

    @Test
    public void testConfig() {

        String file = "src/test/resources/datafiles/Config1.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            Config1Pojo1 pojo = mapper.readValue(new FileReader(file), Config1Pojo1.class);
            System.out.println(pojo.getUrl());
            System.out.println(pojo.getUsername());
            System.out.println(pojo.getPassword());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testLombok() {
        String file = "src/test/resources/datafiles/Config1.json";

        ObjectMapper mapper = new ObjectMapper();
        try {
            Config1PojoLombok1 pojo = mapper.readValue(new FileReader(file), Config1PojoLombok1.class);
            System.out.println(pojo.getUrl());
            System.out.println(pojo.getUsername());
            System.out.println(pojo.getPassword());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testconPojo() throws IOException {
        String file = "src/test/resources/datafiles/config.json";

        ObjectMapper mapper = new ObjectMapper();

        ConfigPojo1 p1 = mapper.readValue(new FileReader(file), ConfigPojo1.class);

        System.out.println("pojo = " + p1);
        System.out.println("p1.getUsers().get(1).getAdress().getCadde() = " + p1.getUsers().get(1).getAdress().getCadde());

    }

    @Test
    public void testMapGeneral1(){
        String file = "src/test/resources/datafiles/Config1.json";

        MyJSONPojo pojo=new Config1PojoLombok1();

        Config1PojoLombok1 data = (Config1PojoLombok1)getPojo(file, pojo);
        System.out.println("data.getUrl() = " + data.getUrl());


    }

    public Object getPojo(String file, MyJSONPojo pojo){
             ObjectMapper mapper=new ObjectMapper();
        try {
            return mapper.readValue(new FileReader(file),pojo.getClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

