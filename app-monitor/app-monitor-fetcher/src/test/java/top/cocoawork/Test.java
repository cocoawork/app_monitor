package top.cocoawork;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.cocoawork.Constant.Constant;
import top.cocoawork.service.AppDataFetchService;
import top.cocoawork.service.RemoteEmailService;
import top.cocoawork.task.ScheduleTask;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private AppDataFetchService appDataFetchService;

    @Autowired
    private ScheduleTask task;

    @Autowired
    private RemoteEmailService emailService;

    @org.junit.Test
    public void conetxtLoad(){}


    @org.junit.Test
    public void sendMQ(){
        try {
//            appDataFetchService.fetchAppOutline("cn", Constant.MediaType.IOS_APP, Constant.FeedType.TOP_FREE);
//            task.scheduleFetchAppOutline();
//            task.scheduleFetchAppInfo();


            emailService.sendEmail("657633723@qq.com", "fffff");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
