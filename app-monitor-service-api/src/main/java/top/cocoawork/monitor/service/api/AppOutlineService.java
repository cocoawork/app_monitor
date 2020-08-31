package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.dto.AppOutlineDto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AppOutlineService {
    /**
    * @Description: 插入
    * @Param: [appOutline]
    * @return: boolean
    */
    AppOutlineDto insert(@Valid @NotNull(message = "插入对象不能为空") AppOutlineDto appOutline);

    /**
    * @Description: 更新
     * @Param: [appOutline]根据主键更新，主键不能为空
    * @return: boolean
    */
    AppOutlineDto update(@NotNull(message = "更新对象不能为空")AppOutlineDto appOutline);

    /**
    * @Description: 根据appid删除数据
    * @Param: [appId]
    * @return: boolean
    */
    boolean deleteById(@NotNull(message = "id不能为空")String appId);

    /**
    * @Description: 根据appid查找
    * @Param: [appId]
    * @return: top.cocoawork.model.AppOutline
    */
    AppOutlineDto selectById(@NotNull(message = "id不能为空")String appId);

    /**
    * @Description: 分页查询列表
    * @Param: [countryCode, pageIndex, pageSize]
    * @return: java.util.List<top.cocoawork.model.AppOutline>
    */
    List<AppOutlineDto> selectPage(String countryCode, @Min(0) Integer pageIndex, @Min(1) @Max(100) Integer pageSize);

    /**
    * @Description: 查询全部数据id
    * @return: java.util.List<top.cocoawork.model.AppOutline>
    */
    List<String> selectAllIds();
}
