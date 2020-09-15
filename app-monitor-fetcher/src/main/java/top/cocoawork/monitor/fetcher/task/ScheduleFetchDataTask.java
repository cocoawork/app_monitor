package top.cocoawork.monitor.fetcher.task;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.common.enums.AppType;
import top.cocoawork.monitor.fetcher.domain.Email;
import top.cocoawork.monitor.service.api.AppOutlineService;
import top.cocoawork.monitor.service.api.CountryService;

import top.cocoawork.monitor.fetcher.service.AppDataFetchService;
import top.cocoawork.monitor.service.api.dto.CountryDto;
import top.cocoawork.monitor.util.mgr.CustomThreadPool;


import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ScheduleFetchDataTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleFetchDataTask.class);

    @Autowired
    private AppDataFetchService appDataFetcheService;

    @Reference
    private CountryService countryService;

    @Reference
    private AppOutlineService appOutlineService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RedisTemplate<String, CountryDto> redisTemplate;

    //对象销毁前执行
    @PreDestroy
    public void destroy() {
        //对象销毁之前关闭线程池
        CustomThreadPool.shareInstance().shutdownNow();
    }

    /**
    * @Description: 定时任务获取数据，每天0,12点执行一次
    * @Param: []
    * @return: void
    */
    @Scheduled(cron = "0 0 0,12 * * *")
    public void scheduleFetchAppOutline() {

        List<AppType.FeedType> appFeedTypeList = new ArrayList<>();
        appFeedTypeList.add(AppType.FeedType.NEW_APPS_WE_LOVE);
        appFeedTypeList.add(AppType.FeedType.NEW_GAME_WE_LOVE);
        appFeedTypeList.add(AppType.FeedType.TOP_FREE);
        appFeedTypeList.add(AppType.FeedType.TOP_FREE_IPAD);
        appFeedTypeList.add(AppType.FeedType.TOP_GROSSING);
        appFeedTypeList.add(AppType.FeedType.TOP_GROSSING_IPAD);

        //获取所有国家
        String key = "top.cocoawork.monitor.fetcher.countriesKey";
        List<CountryDto> countries = redisTemplate.opsForList().range(key, 0, -1);

        if (null == countries || countries.isEmpty()) {
            countries = countryService.selectAll();
            redisTemplate.opsForList().leftPushAll(key, countries);
        }
        if (null == countries || countries.size() == 0) {
            return;
        }


        //获取开始时间
        LocalDateTime start = LocalDateTime.now();

        logger.info("开始执行定时任务获取app简介信息{}", start.toString());

        for (CountryDto country : countries) {
            for (AppType.FeedType feedType : appFeedTypeList) {

                CustomThreadPool.shareInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        appDataFetcheService.fetchAppOutline(country.getCountryCode(), AppType.MediaType.IOS_APP, feedType);
                    }
                });
            }
        }

        while (true) {
            int activeCount = CustomThreadPool.shareInstance().getActiveCount();

            logger.debug("当前线程池中任务个数：{}", activeCount);

            if (activeCount == 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //获取结束时间
        LocalDateTime end = LocalDateTime.now();

        logger.info("完成执行定时任务获取app简介信息{}", end.toString());

        //记录本次请求记录
//        DataFetchRecoderDto recoder = new DataFetchRecoderDto();
//        recoder.setBeginTime(start);
//        recoder.setEndTime(end);
//        recoder.setType(AppOutlineDto.class.getName());
//        dataFetchRecoderService.insert(recoder);

        String startString = start.getHour() + ":" + start.getMinute();
        String endString = end.getHour() + ":" + end.getMinute();

        //邮件通知任务完成
        Email email = new Email();
        email.setTo("657633723@qq.com");
        email.setSubject("任务完成");
        String text = "完成当前批次任务「获取app简介信息」！\n开始时间：" + startString + "\n" + "结束时间：" + endString;
        email.setContent(text);
        rocketMQTemplate.sendOneWay(ApplicationConstant.MQ_TOPIC+":"+ApplicationConstant.MQ_TOPIC_TAG_EMAIL, email);

    }


    //每天0点后每隔8小时执行一次
    @Scheduled(cron = "0 0 0/8 * * *")
    public void scheduleFetchAppInfo() {


        //执行获取详情
        List<String> ids = appOutlineService.selectAllIds();

        if (null == ids || ids.size() == 0) {
            return;
        }

        //记录开始时间
        LocalDateTime begin = LocalDateTime.now();
        logger.info("开始执行定时任务获取app详细信息{}", begin.toString());

        for (String id : ids) {

            String appid = id;
            CustomThreadPool.shareInstance().execute(() -> {
                appDataFetcheService.fetchAppInfo(appid);
            });
        }

        while (true) {
            int activeCount = CustomThreadPool.shareInstance().getActiveCount();
            if (activeCount == 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //记录结束时间
        LocalDateTime end = LocalDateTime.now();


        logger.info("完成执行定时任务获取app详细信息{}", end.toString());

//
//        //记录本次请求记录
//        DataFetchRecoderDto recoder = new DataFetchRecoderDto();
//        recoder.setBeginTime(begin);
//        recoder.setEndTime(end);
//        recoder.setType(AppInfoDto.class.getName());
//        dataFetchRecoderService.insert(recoder);

    }



}
