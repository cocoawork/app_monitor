package com.cocoawork.appstore.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppOutline;
import com.cocoawork.appstore.entity.Genre;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.mapper.AppOutlineMapper;
import com.cocoawork.appstore.mapper.GenreMapper;
import com.cocoawork.appstore.service.AppOutlineService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppOutlineServiceImpl implements AppOutlineService {

    public static final String APP_STORE_BASE_URL = "https://rss.itunes.apple.com/api/v1/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppOutlineMapper appOutlineMapper;

    @Autowired
    private GenreMapper genreMapper;


    @Override
    public List<AppOutline> fetchAppsFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType) throws Exception {

        String url = APP_STORE_BASE_URL + countryCode + "/" + mediaType.getRawValue() + "/" + feedType.getRawValue() + "/all/100/explicit.json";
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer addAppRecoder(AppOutline appOutline) {
        try {
            Integer result = appOutlineMapper.addAppRecoder(appOutline);
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
    public AppOutline getOne() {
        return appOutlineMapper.getOne();
    }

    @Override
    public AppOutline getAppById(String appId) {
        return appOutlineMapper.getAppById(appId);
    }

    @Override
    public List<AppOutline> getApps(String countryCode, String mediaType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AppOutline> apps = appOutlineMapper.getApps(countryCode, mediaType);
        return apps;
    }

    @Override
    public Integer deleteAppById(String appId) {
        return appOutlineMapper.deleteAppById(appId);
    }


}
