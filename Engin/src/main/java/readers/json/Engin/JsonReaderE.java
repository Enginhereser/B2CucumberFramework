package readers.json.Engin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReaderE {

    @Test
    public void read() throws Exception {
        String file="src/test/resources/datafiles/Config1.json";

        JSONObject object=(JSONObject) JSONValue.parse(new FileReader(file));
        System.out.println(object);
        System.out.println("------------");
        System.out.println(object.get("url"));
        System.out.println(object.get("username"));
        System.out.println(object.get("password"));
    }
    @Test
    public void read2() throws FileNotFoundException {
        String file="src/test/resources/datafiles/Config2.json";
        JSONObject json=(JSONObject) JSONValue.parse(new FileReader(file));
        System.out.println(json.get("types"));
        JSONArray types=(JSONArray) json.get("types");
        for (Object type : types) {
            System.out.println(type);
        }
        System.out.println("------------------");

        JSONArray users =(JSONArray) json.get("users");


        for (Object user : users){
            JSONObject userobj =(JSONObject) user;
            System.out.println(userobj.get("username"));

        }

    }
}
