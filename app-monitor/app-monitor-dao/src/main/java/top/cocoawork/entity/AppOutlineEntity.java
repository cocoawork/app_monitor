package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@TableName("t_app_outline")
public class AppOutlineEntity extends BaseAutoEntity {

    @TableId(value = "app_id")
    private String appId;
    private String kind;

    private String artistName;

    private String name;

    private String copyright;

    private String countryCode;

    private String mediaType;

    private String feedType;

    private String artistId;

    private String artistUrl;

    private String artworkUrl100;

    private String url;

    @TableField(select = false)
    private List<GenreEntity> genres;

}
