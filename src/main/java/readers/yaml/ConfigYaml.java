package readers.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import readers.MyPojo;

@Getter
@Setter
@ToString
public class ConfigYaml extends MyPojo {

    private Application application;
    private Users users;
    private Browsers browsers;


    @Getter
    @Setter
    @ToString
    public static class Application {
        private String url;
    }


    @Getter
    @Setter
    @ToString
    public static class Users {
        private User user1;
        private User user2;
    }

    @Getter
    @Setter
    @ToString
    public static class User {
        private String username;
        private String password;
    }


    @Getter
    @Setter
    @ToString
    public static class Browsers {
        private Browser chrome;
        private Browser edge;
    }

    @Getter
    @Setter
    @ToString
    public static class Browser {
        private String name;
        private boolean headless;
        private String userDataDir;

    }


}
