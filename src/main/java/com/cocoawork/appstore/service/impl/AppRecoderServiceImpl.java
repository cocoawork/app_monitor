package com.cocoawork.appstore.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.AppRecoder;
import com.cocoawork.appstore.entity.Genre;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.mapper.AppRecoderMapper;
import com.cocoawork.appstore.mapper.GenreMapper;
import com.cocoawork.appstore.service.AppRecoderService;
import com.cocoawork.appstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppRecoderServiceImpl implements AppRecoderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppRecoderMapper appRecoderMapper;

    @Autowired
    private GenreMapper genreMapper;


    @Override
    public List<AppRecoder> fetchAppsFromRemote(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType) throws Exception {

        String url = Constant.APP_STORE_BASE_URL + countryCode + "/" + mediaType.getRawValue() + "/" + feedType.getRawValue() + "/all/100/explicit.json";
        String resultString = restTemplate.getForObject(url, String.class);
        if (null == resultString || "".equals(resultString)) {
            throw new CustomException("请求数据失败");
        }

        JSONObject jsonObject = JSONObject.parseObject(resultString);
        JSONObject feedJsonObject = (JSONObject) jsonObject.get("feed");

        JSONArray resultJsonObject = (JSONArray) feedJsonObject.get("results");
        List<AppRecoder> appRecoders = JSONArray.parseArray(resultJsonObject.toString(), AppRecoder.class);

        for (AppRecoder appRecoder : appRecoders) {
            appRecoder.setUpdateTime(LocalDateTime.now());
            appRecoder.setCountryCode(countryCode);
            appRecoder.setMediaType(mediaType.getRawValue());
        }
        return appRecoders;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Integer addAppRecoder(AppRecoder appRecoder) {
        try {
            Integer result = appRecoderMapper.addAppRecoder(appRecoder);
            for (Genre genre: appRecoder.getGenres()) {
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


}
