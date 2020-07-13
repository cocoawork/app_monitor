package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_user")
public class UserEntity extends BaseAutoEntity {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    private String username;
    private String password;
    private String email;
    private String profile;
    private Integer gender;
    private Integer age;

}
