package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoEntity;

@Data
@NoArgsConstructor
@TableName("t_user_role")
public class UserRole extends BaseAutoEntity {

    private Long userId;
    private String userRole;

}
