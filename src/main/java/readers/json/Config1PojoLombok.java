package readers.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import readers.MyPojo;

@Setter
@Getter
@ToString
public class Config1PojoLombok extends MyPojo {
    // lombok annotationslar kullanilarak getter, setter, toString ve error islemlerinde kolaylik saglar
    String url;
    String username;
    String password;

}
