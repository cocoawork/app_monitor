package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@TableName("t_user_app")
public class UserAppEntity extends BaseAutoEntity {

    private String userId;
    private String appId;

    @TableField(select = false)
    private AppOutlineEntity appOutline;

}
