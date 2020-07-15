package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_user_role")
public class UserRoleEntity extends BaseAutoEntity{

    private String userId;
    private String userRole;


}
