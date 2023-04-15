package readers;

import org.testng.annotations.Test;
import readers.json.Config2Pojo;
import readers.yaml.ConfigYaml;
import utils.Utils;

public class TestJsonYamlConvert {


    /**
     * okunan .json dosyasi .yaml olarak yazilir
     */
    @Test
    public void testJsonToYaml(){
        String fileJson = "src/test/resources/datafiles/Config2.json";
        String fileYaml = "src/test/resources/datafiles/convert/Config2FromJson.yaml";
        MyPojo pojo = new Config2Pojo();
        Config2Pojo config = (Config2Pojo) Utils.getPojo(fileJson, pojo);
        System.out.println(config);
        Utils.writePojo(fileYaml, config);
    }

    /**
     * okunan .yaml dosyasi .json olarak yazilir
     */
    @Test
    public void testYamlToJson(){
        String fileYaml = "src/test/resources/datafiles/Config.yaml";
        String fileJson = "src/test/resources/datafiles/convert/ConfigFromYaml.json";
        MyPojo pojo = new ConfigYaml();
        ConfigYaml config = (ConfigYaml) Utils.getPojo(fileYaml, pojo);
        System.out.println(config);
        Utils.writePojo(fileJson, config);
    }

}
