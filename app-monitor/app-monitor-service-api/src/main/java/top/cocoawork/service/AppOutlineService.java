package top.cocoawork.service;

import top.cocoawork.model.AppOutline;

import java.util.List;

public interface AppOutlineService {
    /**
    * @Description: 插入
    * @Param: [appOutline]
    * @return: boolean
    */
    boolean insertAppOutline(AppOutline appOutline);

    /**
    * @Description: 更新
     * @Param: [appOutline]根据主键更新，主键不能为空
    * @return: boolean
    */
    boolean updateAppOutline(AppOutline appOutline);

    /**
    * @Description: 根据appid删除数据
    * @Param: [appId]
    * @return: boolean
    */
    boolean deleteAppOutlineByAppId(String appId);

    /**
    * @Description: 根据appid查找
    * @Param: [appId]
    * @return: top.cocoawork.model.AppOutline
    */
    AppOutline selectAppOutlineByAppId(String appId);

    /**
    * @Description: 分页查询列表
    * @Param: [countryCode, pageIndex, pageSize]
    * @return: java.util.List<top.cocoawork.model.AppOutline>
    */
    List<AppOutline> selectAppOutlinesByPage(String countryCode, Integer pageIndex, Integer pageSize);

    /**
    * @Description: 查询全部数据id
    * @return: java.util.List<top.cocoawork.model.AppOutline>
    */
    List<String> selectAllAppOutlineAppIds();
}
