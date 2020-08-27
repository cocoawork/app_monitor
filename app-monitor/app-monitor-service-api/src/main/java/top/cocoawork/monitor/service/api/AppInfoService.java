package top.cocoawork.monitor.service.api;



import top.cocoawork.monitor.service.api.model.AppInfoDto;

import java.util.List;

public interface AppInfoService {


    /**
    * @Description: 插入信息
    * @Param: [appinfo]
    * @return: boolean
    */
    AppInfoDto insert(AppInfoDto appinfo);

    /**
    * @Description: 根据appid删除
    * @Param: [appId]
    * @return: boolean
    */
    boolean deleteById(String appId);

    /**
    * @Description: 更新appInfo
    * @Param: [appinfo]
    * @return: boolean
    */
    AppInfoDto update(AppInfoDto appinfo);

    /**
    * @Description: 根据appId查询
    * @Param: [appId]
    * @return: top.cocoawork.model.AppInfo
    */
    AppInfoDto selectById(String appId);

    /**
     * @Description: 根据bundleId查询
     * @Param: [根据bundleId查询]
     * @return: top.cocoawork.model.AppInfo
     */
    AppInfoDto selectByBundleId(String bundleId);

    /**
    * @Description: 分页 查询
    * @Param: [页码（从0开始）, 每页数据个数]
    * @return: java.util.List<top.cocoawork.model.AppInfo>
    */
    List<AppInfoDto> selectPage(Integer pageIndex, Integer pageSize);

}
