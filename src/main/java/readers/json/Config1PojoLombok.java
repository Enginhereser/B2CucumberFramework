package readers.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Config1PojoLombok extends MyJsonPojo {
    // lombok annotationslar kullanilarak getter, setter, toString ve error islemlerinde kolaylik saglar
    String url;
    String username;
    String password;

}
