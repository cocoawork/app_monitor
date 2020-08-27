package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoEntity;

@Data
@NoArgsConstructor
@TableName("t_user_app")
public class UserApp extends BaseAutoEntity {

    private String userId;
    private String appId;

    @TableField(select = false)
    private AppOutline appOutline;

}
