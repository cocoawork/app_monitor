package top.cocoawork.monitor.dao.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseAutoIdEntity extends BaseAutoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

}
