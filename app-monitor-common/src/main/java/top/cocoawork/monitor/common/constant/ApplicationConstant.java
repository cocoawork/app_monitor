package top.cocoawork.monitor.common.constant;

public class ApplicationConstant {

    //请求头中token字段key
    public final static String REQUEST_HEADER_TOKEN_KEY = "token";
    //解析token后获取userid存储到request域中的key
    public final static String REQUEST_HEADER_UID_KEY = "userId";

    public static final String USER_ROLE_USER = "user";
    public static final String USER_ROLE_ADMIN = "admin";
    public static final String USER_ROLE_VIP = "vip";

    public static final String MQ_TOPIC = "app-monitor-mq-topic";
    public static final String MQ_TOPIC_TAG_EMAIL = "topic-tag-email";
    public static final String MQ_TOPIC_TAG_APPINFO = "topic-tag-appinfo";
    public static final String MQ_TOPIC_TAG_APPOUTLINE = "topic-tag-appoutline";

}
