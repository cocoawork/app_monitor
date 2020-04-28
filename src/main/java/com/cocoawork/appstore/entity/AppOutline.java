package com.cocoawork.appstore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppOutline {

    private String artistName;

    @JSONField(name = "id")
    private String appId;

    private String name;

    private String kind;

    private String copyright;

    private String countryCode;

    private String mediaType;

    private String feedType;

    private String artistId;

    private String artistUrl;

    private String artworkUrl100;

    private String url;

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    private List<Genre> genres;
}
