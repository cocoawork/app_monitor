package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.dto.UserFavourDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserFavourService {

    /**
    * @Description: 添加用户关注
    * @Param: [userApp]
    * @return: boolean
    */
    boolean insert(@Valid @NotNull(message = "插入对象不能为空") UserFavourDto userApp);

    /**
    * @Description: 删除用户关注
    * @Param: [userApp]
    * @return: boolean
    */
    boolean deleteById(UserFavourDto userApp);

    /**
    * @Description: 根据用户id查询关注列表
    * @Param: [userId]
    * @return: java.util.List<top.cocoawork.model.UserApp>
    */
    List<UserFavourDto> selectUserAppsByUserId(Long userId);

    /**
    * @Description: 根据appid查询用户关注
    * @Param: [appId]
    * @return: java.util.List<top.cocoawork.model.UserApp>
    */
    List<UserFavourDto> selectUserAppsByAppId(String appId);
}
