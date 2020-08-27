package top.cocoawork.monitor.service.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDto extends BaseModelDto implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String profile;
    private Integer gender;
    private Integer age;

}
