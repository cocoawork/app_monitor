package top.cocoawork.monitor.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.dao.entity.base.BaseAutoEntity;
import top.cocoawork.monitor.dao.conf.StringArrayTypeHandler;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value = "t_app_info", autoResultMap = true)
public class AppInfo extends BaseAutoEntity {

    private String id;
    private String kind;
    private String trackName;
    private String bundleId;
    private String artistId;

    @TableField(typeHandler = StringArrayTypeHandler.class)
    private List<String> screenshotUrls;
    private String artworkUrl512;
    private String artworkUrl60;
    private String artworkUrl100;

    @TableField(typeHandler = StringArrayTypeHandler.class)
    private List<String> supportedDevices;
    private double fileSizeBytes;
    private double averageUserRating;
    private String formattedPrice;
    private String minimumOsVersion;
    private String currentVersionReleaseDate;
    private String releaseNotes;
    private String description;
    private String contentAdvisoryRating;
    private String version;
}
