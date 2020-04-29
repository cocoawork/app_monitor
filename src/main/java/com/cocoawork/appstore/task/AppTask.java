package com.cocoawork.appstore.task;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.entity.Country;
import com.cocoawork.appstore.service.AppService;
import com.cocoawork.appstore.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AppTask {

    private static final Logger logger = LoggerFactory.getLogger(AppTask.class);

    @Autowired
    private AppService appService;

    @Autowired
    private CountryService countryService;

    /*
    * 定时任务，每天凌晨0点获取数据
    * */
    @Scheduled(cron = "0 0 0 * * *")
    public void fetchApp() {
        //获取当前时间
        LocalDateTime now = LocalDateTime.now();
        logger.info(now.toString() + "开始获取app数据");

        List<Country> countryList = countryService.getAllCountry();


        List<Constant.FeedType> appFeedTypeList = new ArrayList<>();
        appFeedTypeList.add(Constant.FeedType.NEW_APPS_WE_LOVE);
        appFeedTypeList.add(Constant.FeedType.NEW_GAME_WE_LOVE);
        appFeedTypeList.add(Constant.FeedType.TOP_FREE);
        appFeedTypeList.add(Constant.FeedType.TOP_FREE_IPAD);
        appFeedTypeList.add(Constant.FeedType.TOP_GROSSING);
        appFeedTypeList.add(Constant.FeedType.TOP_GROSSING_IPAD);

        List<Constant.FeedType> musicFeedTypeList = new ArrayList<>();
        musicFeedTypeList.add(Constant.FeedType.COMING_SOON);
        musicFeedTypeList.add(Constant.FeedType.HOT_TRACK);
        musicFeedTypeList.add(Constant.FeedType.NEW_RELEASE);
        musicFeedTypeList.add(Constant.FeedType.TOP_ALBUM);
        musicFeedTypeList.add(Constant.FeedType.TOP_SONG);

        List<Constant.FeedType> itunesUFeedTypeList = new ArrayList<>();
        itunesUFeedTypeList.add(Constant.FeedType.ITUNES_U_COURSE);

        List<Constant.FeedType> podcastFeedTypeList = new ArrayList<>();
        podcastFeedTypeList.add(Constant.FeedType.TOP_PODCAST);

        Map<Constant.MediaType, List<Constant.FeedType>> map = new HashMap<>();
        map.put(Constant.MediaType.IOS_APP, appFeedTypeList);
        map.put(Constant.MediaType.APPLE_MUSIC, musicFeedTypeList);
        map.put(Constant.MediaType.ITUNES_U, itunesUFeedTypeList);
        map.put(Constant.MediaType.PODCAST, podcastFeedTypeList);


        for (Country country : countryList) {

            for (Constant.MediaType mediaType: map.keySet()) {
                List<Constant.FeedType> feedTypeList = map.get(mediaType);
                for (Constant.FeedType feedType: feedTypeList) {
                    try {
                        log.info("开始获取数据》》国家：" + country.getCountryName() + "| media类型：" + mediaType.getRawValue() + "| feed类型：" + feedType.getRawValue());
                        appService.fetchAppsFromRemote(country.getCountryCode(), mediaType, feedType);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        logger.info("结束获取app数据");
    }

}
