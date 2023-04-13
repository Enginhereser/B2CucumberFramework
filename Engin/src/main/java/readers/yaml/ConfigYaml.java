package readers.yaml;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigYaml {

    private Application application;
    private User user1;
    private User user2;
    private Browser chrome;
    private Browser edge;

    @Getter
    @Setter
    public class Application {
        private String url;
    }

    @Getter
    @Setter
    public class User {
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public class Browser {
        private String name;
        private boolean headless;
        private String userDataDir;

    }


}
