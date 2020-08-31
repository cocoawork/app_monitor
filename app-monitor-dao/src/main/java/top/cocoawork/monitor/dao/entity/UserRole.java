package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoEntity;
import top.cocoawork.monitor.dao.entity.base.BaseAutoIdEntity;

@Data
@NoArgsConstructor
@TableName("t_user_role")
public class UserRole extends BaseAutoIdEntity {

    private String role;
    private String tag;
    private String menu;

}
