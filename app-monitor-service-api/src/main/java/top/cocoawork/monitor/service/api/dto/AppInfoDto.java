package top.cocoawork.monitor.service.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@ApiModel(value = "AppInfoDto", description = "app详细信息")
public class AppInfoDto extends BaseModelDto implements Serializable {

    @NotBlank(message = "app id不能为空")
    @ApiModelProperty(value = "appid")
    private String appId;

    @ApiModelProperty(value = "专辑名")
    private String trackName;

    @ApiModelProperty(value = "包id")
    private String bundleId;

    @ApiModelProperty(value = "专辑id")
    private String artistId;

    @ApiModelProperty(value = "类别")
    private String kind;

    @ApiModelProperty(value = "截图")
    private List<String> screenshotUrls;

    @ApiModelProperty(value = "海报")
    private String artworkUrl512;

    @ApiModelProperty(value = "海报1")
    private String artworkUrl60;

    @ApiModelProperty(value = "海报2")
    private String artworkUrl100;

    @ApiModelProperty(value = "支持设备")
    private List<String> supportedDevices;

    @ApiModelProperty(value = "文件大小")
    private double fileSizeBytes;

    @ApiModelProperty(value = "平均评分")
    private double averageUserRating;

    @ApiModelProperty(value = "价格")
    private String formattedPrice;

    @ApiModelProperty(value = "最小支持版本")
    private String minimumOsVersion;

    @ApiModelProperty(value = "当前版本发布时间")
    private String currentVersionReleaseDate;

    @ApiModelProperty(value = "发布说明")
    private String releaseNotes;

    @ApiModelProperty(value = "简介")
    private String description;

    @ApiModelProperty(value = "内容分级")
    private String contentAdvisoryRating;

    @ApiModelProperty(value = "版本")
    private String version;
}
