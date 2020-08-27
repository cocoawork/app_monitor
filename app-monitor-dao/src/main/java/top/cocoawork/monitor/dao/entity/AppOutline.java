package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.cocoawork.monitor.dao.entity.base.BaseAutoEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@TableName("t_app_outline")
public class AppOutline extends BaseAutoEntity {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
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
    private String genres;

    @TableField(exist = false)
    private Set<Genre> genre = new HashSet<>();

}
