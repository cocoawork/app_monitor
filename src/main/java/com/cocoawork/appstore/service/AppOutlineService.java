package com.cocoawork.appstore.service;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppOutline;

import java.util.List;


public interface AppOutlineService {

    public List<AppOutline> fetchAppsFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType) throws Exception;

    public Integer addAppRecoder(AppOutline appOutline);

    public AppOutline getOne();

    public AppOutline getAppById(String appId);

    public List<AppOutline> getApps(String countryCode, String mediaType, Integer pageNum, Integer pageSize);

    public Integer deleteAppById(String appId);

}
