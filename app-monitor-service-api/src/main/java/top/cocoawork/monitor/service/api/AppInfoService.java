package top.cocoawork.monitor.service.api;



import top.cocoawork.monitor.service.api.dto.AppInfoDto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AppInfoService {


    /**
    * @Description: 插入信息
    * @Param: [appinfo]
    * @return: boolean
    */
    AppInfoDto insert(@Valid @NotNull(message = "插入对象不能为空") AppInfoDto appinfo);

    /**
    * @Description: 根据appid删除
    * @Param: [appId]
    * @return: boolean
    */
    boolean deleteById(@NotNull(message = "id不能为空")String appId);

    /**
    * @Description: 更新appInfo
    * @Param: [appinfo]
    * @return: boolean
    */
    AppInfoDto update(@NotNull(message = "更新对象不能为空")AppInfoDto appinfo);

    /**
    * @Description: 根据appId查询
    * @Param: [appId]
    * @return: top.cocoawork.model.AppInfo
    */
    AppInfoDto selectById(@NotNull(message = "id不能为空")String appId);

    /**
     * @Description: 根据bundleId查询
     * @Param: [根据bundleId查询]
     * @return: top.cocoawork.model.AppInfo
     */
    AppInfoDto selectByBundleId(@NotNull(message = "bundle id不能为空")String bundleId);

    /**
    * @Description: 分页 查询
    * @Param: [pageIndex:页码（从0开始）, pageSize:每页数据个数]
    * @return: java.util.List<top.cocoawork.model.AppInfo>
    */
    List<AppInfoDto> selectPage(@Min(0) Integer pageIndex, @Min(1) @Max(100) Integer pageSize);

}
