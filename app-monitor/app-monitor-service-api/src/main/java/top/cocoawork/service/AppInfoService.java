package top.cocoawork.service;


import top.cocoawork.model.AppInfo;

import java.util.List;

public interface AppInfoService {


    /**
    * @Description: 插入信息
    * @Param: [appinfo]
    * @return: boolean
    */
    boolean insertAppInfo(AppInfo appinfo);

    /**
    * @Description: 根据appid删除
    * @Param: [appId]
    * @return: boolean
    */
    boolean deleteAppInfoByAppId(String appId);

    /**
    * @Description: 更新appInfo
    * @Param: [appinfo]
    * @return: boolean
    */
    boolean updateAppInfo(AppInfo appinfo);

    /**
    * @Description: 根据appId查询
    * @Param: [appId]
    * @return: top.cocoawork.model.AppInfo
    */
    AppInfo selectAppInfoByAppId(String appId);

    /**
    * @Description: 分页 查询
    * @Param: [页码（从0开始）, 每页数据个数]
    * @return: java.util.List<top.cocoawork.model.AppInfo>
    */
    List<AppInfo> selectAppInfosByPage(Integer pageIndex, Integer pageSize);

}
