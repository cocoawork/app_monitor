package top.cocoawork.monitor.common.mgr;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPool {

    private volatile static CustomThreadPool SHARE_INSTANCE = null;

    private ThreadPoolExecutor threadPoolExecutor;

    private CustomThreadPool(){
        Runtime runtime = Runtime.getRuntime();
        int coreCount = runtime.availableProcessors();
        int maxSize = 2 * coreCount + 1;
        threadPoolExecutor = new ThreadPoolExecutor(maxSize,
                maxSize,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new CustomThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }

    public static CustomThreadPool shareInstance() {
        if (SHARE_INSTANCE == null) {
            synchronized (CustomThreadPool.class){
                if (SHARE_INSTANCE == null) {
                    SHARE_INSTANCE = new CustomThreadPool();
                }
            }
        }
        return SHARE_INSTANCE;
    }


    public void execute(Runnable command){
        threadPoolExecutor.execute(command);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

    public List<Runnable> shutdownNow(){
        return threadPoolExecutor.shutdownNow();
    }


    public int getActiveCount() {
        return threadPoolExecutor.getActiveCount();
    }


    private static class CustomThreadFactory implements ThreadFactory {

        private static AtomicInteger index = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("top.cocoawork.monitor.thread-" + index.getAndAdd(1));
            return thread;
        }
    }

}
