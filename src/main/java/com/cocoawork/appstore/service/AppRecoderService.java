package com.cocoawork.appstore.service;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppRecoder;
import com.cocoawork.appstore.mapper.AppRecoderMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface AppRecoderService {

    public List<AppRecoder> fetchAppsFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType) throws Exception;

    public Integer addAppRecoder(AppRecoder appRecoder);

    public AppRecoder getOne();

}
