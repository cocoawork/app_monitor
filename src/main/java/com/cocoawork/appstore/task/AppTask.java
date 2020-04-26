package com.cocoawork.appstore.task;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.service.AppRecoderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Async
@Component
public class AppTask {

    private static final Logger logger = LoggerFactory.getLogger(AppTask.class);

    @Autowired
    private AppRecoderService appRecoderService;

    /*
    * 每小时获取一次数据
    * */
    @Scheduled(cron = "0 0 * * * *")
    public void fetchApp() {
        logger.info("开始获取app数据");
        appRecoderService.fetchAppsFromRemote(Constant.CountryCode.CHINA, Constant.MediaType.IOS_APP, Constant.FeedType.NEW_APPS_WE_LOVE);
        logger.info("结束获取app数据");
    }

}
