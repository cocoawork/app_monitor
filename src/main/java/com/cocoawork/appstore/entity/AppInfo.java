package com.cocoawork.appstore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AppInfo {

  private String appId;
  private String trackName;
  private String bundleId;
  private String artistId;
  private String screenshotUrls;
  private String artworkUrl512;
  private String artworkUrl60;
  private String artworkUrl100;
  private String supportedDevices;
  private double fileSizeBytes;
  private double averageUserRating;
  private String formattedPrice;
  private String minimumOsVersion;
  private String currentVersionReleaseDate;
  private String releaseNotes;
  private String description;
  private String contentAdvisoryRating;
}
