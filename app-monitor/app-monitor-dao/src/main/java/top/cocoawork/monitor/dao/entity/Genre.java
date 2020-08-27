package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoIdEntity;

@Data
@NoArgsConstructor
@TableName(value = "t_genre")
public class Genre extends BaseAutoIdEntity {

    private String name;
    private String url;
}
