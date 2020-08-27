package top.cocoawork.monitor.dao.entity.base;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseAutoEntity {

    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    @TableField(value = "update_at", fill = FieldFill.UPDATE)
    private LocalDateTime updateAt;

    @TableLogic
    @TableField(value = "is_delete", select = false)
    private Integer delete;

}
