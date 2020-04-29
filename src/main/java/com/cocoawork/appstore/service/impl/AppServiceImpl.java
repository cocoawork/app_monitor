package com.cocoawork.appstore.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppInfo;
import com.cocoawork.appstore.entity.AppOutline;
import com.cocoawork.appstore.entity.Genre;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.exception.ExceptionEnum;
import com.cocoawork.appstore.mapper.AppInfoMapper;
import com.cocoawork.appstore.mapper.AppOutlineMapper;
import com.cocoawork.appstore.mapper.GenreMapper;
import com.cocoawork.appstore.service.AppService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AppServiceImpl implements AppService {

    public static final String APP_STORE_RSS_BASE_URL = "https://rss.itunes.apple.com/api/v1/";
    public static final String APP_STORE_BASE_LOOKUP_URL = "https://itunes.apple.com/lookup";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppOutlineMapper appOutlineMapper;

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Autowired
    private GenreMapper genreMapper;


    @Override
    public List<AppOutline> fetchAppOutlinesFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType) {

        String url = APP_STORE_RSS_BASE_URL + countryCode + "/" + mediaType.getRawValue() + "/" + feedType.getRawValue() + "/all/100/explicit.json";
        String resultString = restTemplate.getForObject(url, String.class);
        if (null == resultString || "".equals(resultString)) {
            throw new CustomException("请求数据失败");
        }

        JSONObject jsonObject = JSONObject.parseObject(resultString);
        JSONObject feedJsonObject = (JSONObject) jsonObject.get("feed");

        JSONArray resultJsonObject = (JSONArray) feedJsonObject.get("results");
        List<AppOutline> appOutlines = JSONArray.parseArray(resultJsonObject.toString(), AppOutline.class);

        for (AppOutline appOutline : appOutlines) {
            appOutline.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            appOutline.setCountryCode(countryCode);
            appOutline.setMediaType(mediaType.getRawValue());
        }
        return appOutlines;
    }


    public AppInfo fetchAppInfoFromRemote(String appId) throws IOException {
        if (StringUtils.isEmpty(appId)){
            throw new CustomException(ExceptionEnum.INVALID_PARAMETER);
       }
        String url = APP_STORE_BASE_LOOKUP_URL + "?id=" + appId;
        String result = restTemplate.getForObject(url, String.class);
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(result);
        JsonNode results = jsonNode.get("results");
        JsonNode element = results.get(0);
        if (null == element) {
            throw new CustomException(ExceptionEnum.SERVICE_EXCEPTION);
        }
        AppInfo appinfo = om.treeToValue(element, AppInfo.class);
        appinfo.setAppId(appId);
        return appinfo;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer addAppOutline(AppOutline appOutline) {
        try {
            Integer result = appOutlineMapper.addAppOutline(appOutline);
            for (Genre genre: appOutline.getGenres()) {
                try {
                    genreMapper.addGenre(genre);
                }catch (DuplicateKeyException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }catch (DuplicateKeyException e) {
            e.printStackTrace();
            return -1;
        }catch (BadSqlGrammarException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public AppOutline getOneAppOutline() {
        return appOutlineMapper.getOneAppOutline();
    }

    @Override
    public AppOutline getAppOutlineById(String appId) {
        return appOutlineMapper.getAppOutlineById(appId);
    }

    @Override
    public List<AppOutline> getAppOutlines(String countryCode, String mediaType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AppOutline> apps = appOutlineMapper.getAppOutlines(countryCode, mediaType);
        return apps;
    }

    @Override
    public Integer deleteAppOutlineById(String appId) {
        return appOutlineMapper.deleteAppOutlineById(appId);
    }

    @Override
    public Integer addAppInfo(AppInfo appinfo) {
        return appInfoMapper.addAppInfo(appinfo);
    }

    @Override
    public AppInfo getAppInfoById(String appId) {
        AppInfo appinfo = appInfoMapper.getAppInfoById(appId);
        if (null == appinfo) {
            //数据库不存在，去网络请求；
            try {
                appinfo = this.fetchAppInfoFromRemote(appId);
                appInfoMapper.addAppInfo(appinfo);
                return appinfo;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appinfo;
    }

    @Override
    public Integer deleteAppInfoById(String appId) {
        return appInfoMapper.deleteAppInfoById(appId);
    }

    @Transactional
    @Override
    public Integer deleteAppById(String appId) {
        Integer ret1 = appOutlineMapper.deleteAppOutlineById(appId);
        Integer ret2 = appInfoMapper.deleteAppInfoById(appId);
        return (ret1 > 0 || ret2 > 0) ? 1 : 0;
    }


}
