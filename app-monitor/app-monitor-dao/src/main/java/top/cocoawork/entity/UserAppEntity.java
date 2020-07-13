package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("t_user_app")
public class UserAppEntity extends BaseAutoEntity {

    private String userId;
    private String appId;

}
