package top.cocoawork.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.cocoawork.Constant.Constant;
import top.cocoawork.model.AppInfo;
import top.cocoawork.model.AppOutline;
import top.cocoawork.model.User;
import top.cocoawork.model.UserApp;
import top.cocoawork.service.*;

import java.io.IOException;
import java.util.*;

@Service
public class AppDataFetcheServiceImpl implements AppDataFetchService {

    private Logger logger = LoggerFactory.getLogger(AppDataFetcheServiceImpl.class);

    public static final String APP_STORE_RSS_BASE_URL = "https://rss.itunes.apple.com/api/v1/";
    public static final String APP_STORE_BASE_LOOKUP_URL = "https://itunes.apple.com/lookup";

    @Autowired
    private RestTemplate restTemplate;

    @Reference
    private AppOutlineService appOutlineService;

    @Reference
    private AppInfoService appInfoService;

    @Reference
    private UserAppService userAppService;

    @Reference
    private UserService userService;

    @Autowired
    private RemoteEmailService emailService;



    @Override
    public void fetchAppOutline(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType){

        logger.info("开始抓取app简介信息==>>国家{}|类型{}", countryCode, feedType);

        String url = APP_STORE_RSS_BASE_URL + countryCode + "/" + mediaType.getRawValue() + "/" + feedType.getRawValue() + "/all/100/explicit.json";
        String resultString = restTemplate.getForObject(url, String.class);


        logger.debug("抓取app简介信息结果：{}", resultString);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(resultString);
        } catch (IOException e) {
            logger.error("app简介信息json解析错误",e);
            return;
        }
        JsonNode feedNode = rootNode.get("feed");
        JsonNode resultNode = feedNode.get("results");
        List<AppOutline> appOutlines = new ArrayList<>();
        if (resultNode.isArray()) {
            Iterator<JsonNode> iterator = resultNode.iterator();
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                String appId = node.get("id").textValue();
                AppOutline appOutline = null;
                try {
                    appOutline = objectMapper.treeToValue(node, AppOutline.class);
                } catch (JsonProcessingException e) {
                    logger.error("获取app详细信息json转AppOutline错误",e);
                    return;
                }
                appOutline.setAppId(appId);
                appOutline.setFeedType(feedType.getRawValue());
                appOutline.setCountryCode(countryCode);
                appOutline.setMediaType(mediaType.getRawValue());
                appOutlines.add(appOutline);
                AppOutline existAppOutline = appOutlineService.selectAppOutlineByAppId(appId);
                if (null != existAppOutline) {
                    appOutlineService.updateAppOutline(appOutline);
                } else {
                    appOutlineService.insertAppOutline(appOutline);
                }
            }
        }
    }

    @Override
    public void fetchAppInfo(String appId) {

        String url = APP_STORE_BASE_LOOKUP_URL + "?id=" + appId;
        String result = restTemplate.getForObject(url, String.class);
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = null;
        try {
            jsonNode = om.readTree(result);
        } catch (IOException e) {
            logger.error("app详细信息json解析错误",e);
            return;
        }
        JsonNode results = jsonNode.get("results");
        JsonNode element = results.get(0);
        if (null != element){
            AppInfo appinfo = null;
            try {
                appinfo = om.treeToValue(element, AppInfo.class);
            } catch (JsonProcessingException e) {
                logger.error("获取app详细信息json转AppInfo错误",e);
                return;
            }
            appinfo.setAppId(appId);

                //根据appid查询appinfo
                AppInfo existAppInfo = appInfoService.selectAppInfoByAppId(appId);
                if (null != existAppInfo){
                    //比较两个对象的日期，判断是否发生版本更新
                    String oldVersion = existAppInfo.getVersion();
                    String newVersion = appinfo.getVersion();
                    int ret = oldVersion.compareTo(newVersion);
                    if (ret < 0) {
                        //发生版本更新
                        //1.根据appid查找关注的用户
                        //2.根据userid查找用户email
                        //3.根据email想用户发送邮件提醒更新
                        List<UserApp> userApps = userAppService.selectUserAppsByAppId(appId);
                        for (UserApp userApp: userApps) {

                            String userId = userApp.getUserId();
                            User user = userService.selectUserByUserId(userId);
                            if (null != user) {

                                String email = user.getEmail();
                                if (null != email && email.length() > 0) {
                                    //发送邮件
                                    String text = "您关注的App：\"" + appinfo.getTrackName() + "\"已经更新了，快去看看吧！链接：" + "";
                                    emailService.sendEmail(email, "");
                                }
                            }
                        }
                    }
                    appInfoService.updateAppInfo(appinfo);
                }else {
                    appInfoService.insertAppInfo(appinfo);
                }

        }
    }


}
