package readers.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YamlReader {

    public static ConfigYaml readYaml(String fileName){
        ConfigYaml config;
        String path = "src/test/resources/datafiles/" + fileName + ".yaml";
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            config = objectMapper.readValue(file, ConfigYaml.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }


    public static void writeYaml(String fileName, ConfigYaml yamlFile){

        String path = "src/test/resources/datafiles/" + fileName + ".yaml";
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            objectMapper.writeValue(new File(path), yamlFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
