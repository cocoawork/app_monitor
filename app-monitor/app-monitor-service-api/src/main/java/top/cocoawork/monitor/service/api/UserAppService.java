package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.UserAppDto;

import java.util.List;

public interface UserAppService {

    /**
    * @Description: 添加用户关注
    * @Param: [userApp]
    * @return: boolean
    */
    boolean inserUserApp(UserAppDto userApp);

    /**
    * @Description: 删除用户关注
    * @Param: [userApp]
    * @return: boolean
    */
    boolean deleteUserApp(UserAppDto userApp);

    /**
    * @Description: 根据用户id查询关注列表
    * @Param: [userId]
    * @return: java.util.List<top.cocoawork.model.UserApp>
    */
    List<UserAppDto> selectUserAppsByUserId(String userId);

    /**
    * @Description: 根据appid查询用户关注
    * @Param: [appId]
    * @return: java.util.List<top.cocoawork.model.UserApp>
    */
    List<UserAppDto> selectUserAppsByAppId(String appId);
}
