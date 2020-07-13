package top.cocoawork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.IDLType;
import top.cocoawork.conf.StringArrayTypeHandler;

import java.util.List;

@Data
@NoArgsConstructor
@TableName(value = "t_app_info", autoResultMap = true)
public class AppInfoEntity extends BaseAutoEntity {

    @TableId(value = "app_id")
    private String appId;
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
