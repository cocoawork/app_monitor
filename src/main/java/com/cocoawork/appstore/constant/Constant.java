package com.cocoawork.appstore.constant;

import lombok.Data;

public class Constant {

    public final static String SUCCESS = "success";
    public final static String FAILED = "failed";

    public static final String APP_STORE_BASE_URL = "https://rss.itunes.apple.com/api/v1/";

    public enum CountryCode {
        CHINA("cn"),
        BU_DAN("bt"),
        DAN_MAI("dk");

        private String rawValue;
        public String getRawValue() {
            return rawValue;
        }
        public void setRawValue(String rawValue) {
            this.rawValue = rawValue;
        }
        CountryCode(String rawValue) {
            this.rawValue = rawValue;
        }
    }

    /*
    * 媒体类型
    * */
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
