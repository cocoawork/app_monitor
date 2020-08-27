package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.AppOutlineDto;

import java.util.List;

public interface AppOutlineService {
    /**
    * @Description: 插入
    * @Param: [appOutline]
    * @return: boolean
    */
    AppOutlineDto insert(AppOutlineDto appOutline);

    /**
    * @Description: 更新
     * @Param: [appOutline]根据主键更新，主键不能为空
    * @return: boolean
    */
    AppOutlineDto update(AppOutlineDto appOutline);

    /**
    * @Description: 根据appid删除数据
    * @Param: [appId]
    * @return: boolean
    */
    boolean deleteById(String appId);

    /**
    * @Description: 根据appid查找
    * @Param: [appId]
    * @return: top.cocoawork.model.AppOutline
    */
    AppOutlineDto selectById(String appId);

    /**
    * @Description: 分页查询列表
    * @Param: [countryCode, pageIndex, pageSize]
    * @return: java.util.List<top.cocoawork.model.AppOutline>
    */
    List<AppOutlineDto> selectPage(String countryCode, Integer pageIndex, Integer pageSize);

    /**
    * @Description: 查询全部数据id
    * @return: java.util.List<top.cocoawork.model.AppOutline>
    */
    List<String> selectAllIds();
}
