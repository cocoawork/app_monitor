package top.cocoawork.monitor.fetcher.task;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberExecutorScheduler;
import co.paralleluniverse.fibers.FiberScheduler;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;
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

import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    private volatile static Executor threadPoolExecutor;

    private volatile static FiberScheduler exectorScheduler;

    private static AtomicInteger threadIndex = new AtomicInteger(0);

    static {
        if (null == threadPoolExecutor) {
            synchronized (ScheduleFetchDataTask.class) {
                if (null == threadPoolExecutor) {

                    Runtime runtime = Runtime.getRuntime();
                    int coreCount = runtime.availableProcessors();
                    threadPoolExecutor = new ThreadPoolExecutor(coreCount,
                            coreCount,
                            60,
                            TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(),
                            new ThreadFactory() {
                                @Override
                                public Thread newThread(Runnable r) {
                                    Thread thread = new Thread(r);
                                    thread.setName("top.cocoawork.monitor.[thread-]" + threadIndex.getAndAdd(1));
                                    return thread;
                                }
                            },
                            new ThreadPoolExecutor.DiscardPolicy()
                    );

                    String schedulerName = "top.cocoawrok.monitor.fiber.scheduler";
                    exectorScheduler = new FiberExecutorScheduler(schedulerName, threadPoolExecutor);
                }
            }
        }

    }


    /**
    * @Description: 定时任务获取数据，每天0,12点执行一次
    * @Param: []
    * @return: void
    */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 8)
    public void scheduleFetchAppOutline() {

        List<AppType.FeedType> appFeedTypeList = new ArrayList<>();
        appFeedTypeList.add(AppType.FeedType.NEW_APPS_WE_LOVE);
        appFeedTypeList.add(AppType.FeedType.NEW_GAME_WE_LOVE);
        appFeedTypeList.add(AppType.FeedType.TOP_FREE);
        appFeedTypeList.add(AppType.FeedType.TOP_FREE_IPAD);
        appFeedTypeList.add(AppType.FeedType.TOP_GROSSING);
        appFeedTypeList.add(AppType.FeedType.TOP_GROSSING_IPAD);


        List<CountryDto> countries = countryService.selectAll();
        if (null == countries || countries.size() == 0) {
            return;
        }


        //获取开始时间
        LocalDateTime start = LocalDateTime.now();

        logger.info("开始执行定时任务获取app简介信息{}", start.toString());
        String fiberName = "top.cocoawrok.monitor.appOutline.fiber";


        for (CountryDto country : countries) {
            for (AppType.FeedType feedType : appFeedTypeList) {

                Fiber<Void> fiber = new Fiber<Void>(fiberName,  exectorScheduler, (SuspendableCallable<Void>) () -> {
                    appDataFetcheService.fetchAppOutline(country.getCountryCode(), AppType.MediaType.IOS_APP, feedType);
                    return null;
                });
                fiber.start();
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
    @Scheduled(fixedRate = 1000 * 60 * 60 * 8)
    public void scheduleFetchAppInfo() {

        //执行获取详情
        List<String> ids = appOutlineService.selectAllIds();

        if (null == ids || ids.size() == 0) {
            return;
        }

        //记录开始时间
        LocalDateTime begin = LocalDateTime.now();
        logger.info("开始执行定时任务获取app详细信息{}", begin.toString());
        String fiberName = "top.cocoawrok.monitor.appInfo.fiber";

        for (String id : ids) {

            Fiber<Void> fiber = new Fiber<Void>(fiberName, exectorScheduler, (SuspendableCallable<Void>) () -> {
                appDataFetcheService.fetchAppInfo(id);
                return null;
            });
            fiber.start();
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
