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
    private User user1;
    private User user2;
    private Browser chrome;
    private Browser edge;

    @Getter
    @Setter
    @ToString
    public class Application {
        private String url;
    }


    @Getter
    @Setter
    @ToString
    public class User {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    @ToString
    public class Browser {
        private String name;
        private boolean headless;
        private String userDataDir;

    }


}
