package com.cocoawork.appstore;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.service.AppRecoderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppstoreApplicationTests {

    @Autowired
    AppRecoderService appRecoderService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void appServiceTest() {
        appRecoderService.fetchAppsFromRemote(Constant.CountryCode.CHINA, Constant.MediaType.IOS_APP, Constant.FeedType.NEW_GAME_WE_LOVE);
    }

}
