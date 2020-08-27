package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoIdEntity;

@Data
@NoArgsConstructor
@TableName("t_user")
public class User extends BaseAutoIdEntity {

    private String username;
    private String password;
    private String email;
    private String profile;
    private Integer gender;
    private Integer age;

}
