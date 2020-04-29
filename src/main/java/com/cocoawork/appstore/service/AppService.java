package com.cocoawork.appstore.service;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppInfo;
import com.cocoawork.appstore.entity.AppOutline;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;

import java.util.List;


public interface AppService {

    public List<AppOutline> fetchAppOutlinesFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType);

    public Integer addAppOutline(AppOutline appOutline);

    public AppOutline getOneAppOutline();

    public AppOutline getAppOutlineById(String appId);

    public List<AppOutline> getAppOutlines(String countryCode, String mediaType, Integer pageNum, Integer pageSize);

    public Integer deleteAppOutlineById(String appId);

    public Integer addAppInfo(AppInfo appinfo);

    public AppInfo getAppInfoById(String appId);

    public Integer deleteAppInfoById(String appId);

    public Integer deleteAppById(String appId);


}
