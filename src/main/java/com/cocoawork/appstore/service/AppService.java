package com.cocoawork.appstore.service;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppOutline;

import java.util.List;


public interface AppService {

    public List<AppOutline> fetchAppsFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType) throws Exception;

    public Integer addAppOutline(AppOutline appOutline);

    public AppOutline getOneAppOutline();

    public AppOutline getAppOutlineById(String appId);

    public List<AppOutline> getAppOutlines(String countryCode, String mediaType, Integer pageNum, Integer pageSize);

    public Integer deleteAppOutlineById(String appId);

}
