package top.cocoawork.enums;

public class AppType {

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
