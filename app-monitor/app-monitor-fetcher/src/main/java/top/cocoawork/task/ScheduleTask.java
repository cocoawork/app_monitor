package top.cocoawork.task;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.cocoawork.Constant.Constant;
import top.cocoawork.model.AppInfo;
import top.cocoawork.model.AppOutline;
import top.cocoawork.model.Country;
import top.cocoawork.model.DataFetchRecoder;
import top.cocoawork.service.DataFetchRecoderService;
import top.cocoawork.service.RemoteEmailService;
import top.cocoawork.service.impl.AppDataFetcheServiceImpl;
import top.cocoawork.service.AppOutlineService;
import top.cocoawork.service.CountryService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class ScheduleTask {
    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    private ThreadPoolExecutor threadPoolExecutor;

    //信号量，用于控制提交任务速率
    private Semaphore semaphore;

    @PostConstruct
    public void initialize() {
        Runtime runtime = Runtime.getRuntime();
        int coreCount = runtime.availableProcessors();

        int maxSize = 2 * coreCount + 1;

        semaphore = new Semaphore(maxSize);

        threadPoolExecutor = new ThreadPoolExecutor(coreCount,
                    maxSize,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    @Autowired
    private AppDataFetcheServiceImpl appDataFetcheService;

    @Reference
    private CountryService countryService;

    @Reference
    private AppOutlineService appOutlineService;

    @Reference
    private DataFetchRecoderService dataFetchRecoderService;

    @Autowired
    private RemoteEmailService emailService;

    //对象销毁前执行
    @PreDestroy
    public void willDestory() {
        //对象销毁之前关闭线程池
        threadPoolExecutor.shutdownNow();
    }

    /**
    * @Description: 定时任务获取数据，每天1,6,11,16,21点执行一次
    * @Param: []
    * @return: void
    */
    @Scheduled(cron = "0 0 1,6,11,16,21 * * *")
    public void scheduleFetchAppOutline() {

        //获取开始时间
        LocalDateTime start = LocalDateTime.now();

        logger.info("开始执行定时任务获取app简介信息{}", start.toString());

        List<Constant.FeedType> appFeedTypeList = new ArrayList<>();
        appFeedTypeList.add(Constant.FeedType.NEW_APPS_WE_LOVE);
        appFeedTypeList.add(Constant.FeedType.NEW_GAME_WE_LOVE);
        appFeedTypeList.add(Constant.FeedType.TOP_FREE);
        appFeedTypeList.add(Constant.FeedType.TOP_FREE_IPAD);
        appFeedTypeList.add(Constant.FeedType.TOP_GROSSING);
        appFeedTypeList.add(Constant.FeedType.TOP_GROSSING_IPAD);

        //获取所有国家
        List<Country> countries = countryService.selectAllCountry();

        for (Country country : countries) {
            for (Constant.FeedType feedType : appFeedTypeList) {

                this.threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
//                        try {
//                            semaphore.acquire();
//                        } catch (Exception e) {
//                            logger.warn("信号量线程被中断",e);
//                        }
                        appDataFetcheService.fetchAppOutline(country.getCountryCode(), Constant.MediaType.IOS_APP, feedType);
//                        semaphore.release();
                    }
                });
            }
        }

        while (true) {
            int activeCount = threadPoolExecutor.getActiveCount();
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

        //记录本次请求记录
        DataFetchRecoder recoder = new DataFetchRecoder();
        recoder.setBeginTime(start);
        recoder.setEndTime(end);
        recoder.setType(AppOutline.class.getName());
        dataFetchRecoderService.insertDataFetchRecoder(recoder);

        String startString = start.getHour() + ":" + start.getMinute();
        String endString = end.getHour() + ":" + end.getMinute();

        //邮件通知任务完成
        String emailTo = "657633723@qq.com";
        String text = "完成当前批次任务「获取app简介信息」！\n开始时间：" + startString + "\n" + "结束时间：" + endString;
        emailService.sendEmail(emailTo, text);
    }


    @Scheduled(fixedRate = 1000*60*60)
    public void scheduleFetchAppInfo() {

        //记录开始时间
        LocalDateTime begin = LocalDateTime.now();

        //执行获取详情
        List<String> ids = appOutlineService.selectAllAppOutlineAppIds();
        for (String id : ids) {

            String appid = id;
            this.threadPoolExecutor.execute(() -> {
//                try {
//                    semaphore.acquire();
//                } catch (Exception e) {
//                    logger.warn("信号量线程被中断",e);
//                }
                appDataFetcheService.fetchAppInfo(appid);
//                semaphore.release();
            });
        }

        while (true) {
            int activeCount = threadPoolExecutor.getActiveCount();
            if (activeCount == 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (ids.size() == 0) {
            return;
        }
        //记录结束时间
        LocalDateTime end = LocalDateTime.now();

        //记录本次请求记录
        DataFetchRecoder recoder = new DataFetchRecoder();
        recoder.setBeginTime(begin);
        recoder.setEndTime(end);
        recoder.setType(AppInfo.class.getName());
        dataFetchRecoderService.insertDataFetchRecoder(recoder);

    }



}
