package readers;

import org.testng.annotations.Test;
import readers.json.Config2Pojo;
import readers.yaml.ConfigYaml;
import utils.Utils;

public class TestPojoReadWrite {


    /**
     * .json dosyasi okunur ve güncellenip tekrar yazilir
     */
    @Test
    public void testJsonReadWrite(){
        String file = "src/test/resources/datafiles/Config2.json";
        MyPojo pojo = new Config2Pojo();
        Config2Pojo config = (Config2Pojo) Utils.getPojo(file, pojo);
        System.out.println(config);
        config.setPassword("deneme1");
        Utils.writePojo(file, config);
    }

    /**
     * .yaml dosyasi okunur ve güncellenip tekrar yazilir
     */
    @Test
    public void testYamlReadWrite() {
        String file = "src/test/resources/datafiles/Config.yaml";
        MyPojo pojo = new ConfigYaml();
        ConfigYaml config = (ConfigYaml) Utils.getPojo(file, pojo);
        System.out.println(config);
        config.getApplication().setUrl("https://new.url1");
        Utils.writePojo(file, config);
    }

}
