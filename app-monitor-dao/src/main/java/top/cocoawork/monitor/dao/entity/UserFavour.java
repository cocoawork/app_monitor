package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoEntity;
import top.cocoawork.monitor.dao.entity.base.BaseAutoIdEntity;

@Data
@NoArgsConstructor
@TableName("t_user_favour")
public class UserFavour extends BaseAutoIdEntity {
    private Long userId;
    private String appId;

    @TableField(select = false)
    private AppOutline appOutline;

}
