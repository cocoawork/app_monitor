package top.cocoawork.task;

import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.cocoawork.constant.Constant;
import top.cocoawork.enums.AppType;
import top.cocoawork.model.AppInfo;
import top.cocoawork.model.AppOutline;
import top.cocoawork.model.Country;
import top.cocoawork.model.DataFetchRecoder;
import top.cocoawork.service.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class ScheduleFetchDataTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleFetchDataTask.class);

    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private AppDataFetchService appDataFetcheService;

    @Reference
    private CountryService countryService;

    @Reference
    private AppOutlineService appOutlineService;

    @Reference
    private DataFetchRecoderService dataFetchRecoderService;

    @Autowired
    private RemoteEmailService emailService;


    @PostConstruct
    public void initialize() {
        Runtime runtime = Runtime.getRuntime();
        int coreCount = runtime.availableProcessors();

        int maxSize = 2 * coreCount + 1;

        threadPoolExecutor = new ThreadPoolExecutor(coreCount,
                maxSize,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }


    //对象销毁前执行
    @PreDestroy
    public void destroy() {
        //对象销毁之前关闭线程池
        threadPoolExecutor.shutdownNow();
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
        List<Country> countries = countryService.selectAllCountry();
        if (null == countries || countries.size() == 0) {
            return;
        }

        //获取开始时间
        LocalDateTime start = LocalDateTime.now();

        logger.info("开始执行定时任务获取app简介信息{}", start.toString());

        for (Country country : countries) {
            for (AppType.FeedType feedType : appFeedTypeList) {

                this.threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        appDataFetcheService.fetchAppOutline(country.getCountryCode(), AppType.MediaType.IOS_APP, feedType);
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

        logger.info("完成执行定时任务获取app简介信息{}", end.toString());

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


    //每天0点后每隔8小时执行一次
    @Scheduled(cron = "0 0 0/8 * * *")
    public void scheduleFetchAppInfo() {


        //执行获取详情
        List<String> ids = appOutlineService.selectAllAppOutlineAppIds();

        if (null == ids || ids.size() == 0) {
            return;
        }

        //记录开始时间
        LocalDateTime begin = LocalDateTime.now();
        logger.info("开始执行定时任务获取app详细信息{}", begin.toString());

        for (String id : ids) {

            String appid = id;
            this.threadPoolExecutor.execute(() -> {
                appDataFetcheService.fetchAppInfo(appid);
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

        //记录结束时间
        LocalDateTime end = LocalDateTime.now();


        logger.info("完成执行定时任务获取app详细信息{}", end.toString());


        //记录本次请求记录
        DataFetchRecoder recoder = new DataFetchRecoder();
        recoder.setBeginTime(begin);
        recoder.setEndTime(end);
        recoder.setType(AppInfo.class.getName());
        dataFetchRecoderService.insertDataFetchRecoder(recoder);

    }



}
