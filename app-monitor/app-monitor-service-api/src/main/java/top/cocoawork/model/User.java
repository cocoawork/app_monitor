package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User extends BaseModel implements Serializable {

    private String id;
    private String username;
    private String password;
    private String email;
    private String profile;
    private Integer gender;
    private Integer age;

}
