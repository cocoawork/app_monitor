package top.cocoawork.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class AppInfo extends BaseModel implements Serializable {
    private String appId;
    private String trackName;
    private String bundleId;
    private String artistId;
    private String kind;
    private List<String> screenshotUrls;
    private String artworkUrl512;
    private String artworkUrl60;
    private String artworkUrl100;
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
