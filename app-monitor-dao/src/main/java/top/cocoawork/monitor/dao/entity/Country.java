package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.cocoawork.monitor.dao.entity.base.BaseAutoIdEntity;

@Data
@TableName("t_country")
public class Country extends BaseAutoIdEntity {

    private String countryName;
    private String countryCode;

}
