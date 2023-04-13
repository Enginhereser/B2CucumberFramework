package readers.property;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    Properties prop = new Properties();
    String file;

    public static PropertyReader read(){
        return read("config");
    }

    public static PropertyReader read(String fileName){
        return new PropertyReader(fileName);
    }

    public PropertyReader(String fileName){
        try {
            file = "src/test/resources/datafiles/" + fileName + ".properties";
            FileReader fileReader = new FileReader(file);
            prop.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String get(String key){
        return prop.getProperty(key);
    }


    public void set(String key, String value){
        prop.setProperty(key, value);
    }


    public void del(String key){
        prop.remove(key);
    }

    public void store(String comment){
        try {
            FileWriter fileWriter = new FileWriter(file);
            prop.store(fileWriter, comment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
