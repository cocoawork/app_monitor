package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_country")
public class CountryEntity extends BaseAutoEntity {

    private String countryName;
    private String countryCode;

}
