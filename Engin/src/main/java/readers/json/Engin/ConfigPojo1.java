package readers.json.Engin;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ConfigPojo1 extends MyJSONPojo{
  private String url;
  private String username;
  private String password;
  private String takescreenshot;
  private String takescreenshotOn;
  private List<User> users;


  @Getter
  @Setter
  @ToString
  public static class User{
      private String type;
      private  String valid;
      private String username;
      private String password;
      private Adress adress;


      @Getter
      @Setter
      @ToString
      public static class Adress{
          private String city;
          private String cadde;
          private int numara;
      }
  }

}
