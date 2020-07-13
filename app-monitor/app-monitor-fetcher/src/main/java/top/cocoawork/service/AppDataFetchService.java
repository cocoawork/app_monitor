package top.cocoawork.service;

import top.cocoawork.Constant.Constant;

import java.io.IOException;

public interface AppDataFetchService {

    /**
    * @Description: 从apple服务器抓取appoutline数据
    * @Param: [countryCode：国家代码, mediaType 媒体类型, feedType：数据流类型]
     */
    void fetchAppOutline(String countryCode, Constant.MediaType mediaType, Constant.FeedType feedType);

    /**
    * @Description: 根据appid获取appinfo信息从apple服务器
    * @Param: [appId]
    * @return: void
    */
    void fetchAppInfo(String appId);

}
