package readers.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class ConfigPojo extends MyJsonPojo {
    private String url;
    private String username;
    private String password;
    private boolean takescreenshot;
    private String takescreenshotOn;
    private List<User> users;

    @Getter
    @Setter
    @ToString
    public static class User{
        private String type;
        private boolean valid;
        private String username;
        private String password;
        private Adress adress;
    }

    @Getter
    @Setter
    @ToString
    public static class Adress{
        private String city;
        private String cadde;
        private int numara;
    }
}
