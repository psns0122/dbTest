package jdbc.users;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class User {
    private String userid;
    private String username;
    private String userpassword;
    private int age;
    private String email;
}
