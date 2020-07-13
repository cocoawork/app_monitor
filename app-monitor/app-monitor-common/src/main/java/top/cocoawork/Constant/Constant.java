package top.cocoawork.Constant;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Constant {


    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,2,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        for (int i = 0; i < 200; i++) {
            int idx = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("执行任务" + idx);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        System.out.println("线程执行完毕");


    }


    public enum MediaType {
        APPLE_MUSIC("apple-music"),
        IOS_APP("ios-apps"),
        ITUNES_U("itunes-u"),
        PODCAST("podcasts");

        private String rawValue;
        public String getRawValue() {
            return rawValue;
        }
        public void setRawValue(String rawValue) {
            this.rawValue = rawValue;
        }
        private MediaType(String rawValue) {
            this.rawValue = rawValue;
        }
    }

    /*
     * Feed类型
     * */
    public enum FeedType {
        COMING_SOON("coming-soon"),
        HOT_TRACK("hot-tracks"),
        NEW_RELEASE("new-releases"),
        TOP_ALBUM("top-albums"),
        TOP_SONG("top-songs"),
        NEW_APPS_WE_LOVE("new-apps-we-love"),
        NEW_GAME_WE_LOVE("new-games-we-love"),
        TOP_FREE("top-free"),
        TOP_FREE_IPAD("top-free-ipad"),
        TOP_GROSSING("top-grossing"),
        TOP_GROSSING_IPAD("top-grossing-ipad"),
        TOP_PAID("top-paid"),
        ITUNES_U_COURSE("itunes-u-courses"),
        TOP_PODCAST("top-podcasts")
        ;
        private String rawValue;
        public String getRawValue() {
            return rawValue;
        }
        public void setRawValue(String rawValue) {
            this.rawValue = rawValue;
        }
        private FeedType(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
