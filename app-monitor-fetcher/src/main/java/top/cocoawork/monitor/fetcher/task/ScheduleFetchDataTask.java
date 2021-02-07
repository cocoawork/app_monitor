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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.common.enums.AppType;
import top.cocoawork.monitor.fetcher.domain.Email;
import top.cocoawork.monitor.service.api.AppOutlineService;
import top.cocoawork.monitor.service.api.CountryService;

import top.cocoawork.monitor.fetcher.service.AppDataFetchService;
import top.cocoawork.monitor.service.api.dto.AppOutlineDto;
import top.cocoawork.monitor.service.api.dto.CountryDto;
import top.cocoawork.monitor.service.api.dto.DataFetchRecoderDto;

import javax.annotation.PreDestroy;
import java.time.Duration;
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

    private volatile static Executor threadPoolExecutor;

    private static AtomicInteger threadIndex = new AtomicInteger(0);

    static {
        if (null == threadPoolExecutor) {
            synchronized (ScheduleFetchDataTask.class) {
                if (null == threadPoolExecutor) {
                    Runtime runtime = Runtime.getRuntime();
                    int coreCount = runtime.availableProcessors();
                    int maxCount = coreCount * 2 + 1;
                    threadPoolExecutor = new ThreadPoolExecutor(coreCount,
                            maxCount,
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
                }
            }
        }

    }


    @Scheduled(fixedRate = 1000 * 3600 * 8, initialDelay = 1000 * 20)
    public void scheduleFetchAppOutline() throws InterruptedException {

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

        CountDownLatch downLatch = new CountDownLatch(countries.size());

        for (CountryDto country : countries) {
            for (AppType.FeedType feedType : appFeedTypeList) {
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            appDataFetcheService.fetchAppOutline(country.getCountryCode(), AppType.MediaType.IOS_APP, feedType);
                        }finally {
                            downLatch.countDown();
                        }
                    }
                });
            }
        }

        downLatch.await();
        //获取结束时间
        LocalDateTime end = LocalDateTime.now();

        logger.info("获取app简介信息定时任务完成，用时{}分钟", Duration.between(start,end).toMinutes());

    }


    @Scheduled(fixedRate = 1000 * 60 * 60 * 8, initialDelay = 1000 * 20)
    public void scheduleFetchAppInfo() throws InterruptedException {

        //执行获取详情
        List<String> ids = appOutlineService.selectAllIds();

        if (null == ids || ids.size() == 0) {
            return;
        }

        //记录开始时间
        LocalDateTime begin = LocalDateTime.now();
        logger.info("开始执行定时任务获取app详细信息{}", begin.toString());

        CountDownLatch downLatch = new CountDownLatch(ids.size());

        for (String id : ids) {
            threadPoolExecutor.execute(() -> {
                try {
                    appDataFetcheService.fetchAppInfo(id);
                }finally {
                    downLatch.countDown();
                }
            });
        }
        downLatch.await();
        //记录结束时间
        LocalDateTime end = LocalDateTime.now();

        logger.info("获取app详细信息定时任务完成，用时{}分钟", Duration.between(begin,end).toMinutes());
    }



}
