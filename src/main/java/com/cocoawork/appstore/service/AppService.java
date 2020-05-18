package com.cocoawork.appstore.service;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppInfo;
import com.cocoawork.appstore.entity.AppOutline;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

import java.util.List;


public interface AppService {

    List<AppOutline> fetchAppOutlinesFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType);

    Integer addAppOutline(AppOutline appOutline);

    AppOutline getOneAppOutline();

    AppOutline getAppOutlineById(String appId);

    List<AppOutline> getAppOutlines(String countryCode, String mediaType, Integer pageNum, Integer pageSize);

    Integer deleteAppOutlineById(String appId);

    Integer addAppInfo(AppInfo appinfo);

    AppInfo getAppInfoById(String appId);

    Integer deleteAppInfoById(String appId);

    Integer deleteAppById(String appId);


}
