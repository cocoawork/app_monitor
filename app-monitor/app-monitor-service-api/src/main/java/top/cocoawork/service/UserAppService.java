package top.cocoawork.service;

import top.cocoawork.model.UserApp;

import java.util.List;

public interface UserAppService {

    /**
    * @Description: 添加用户关注
    * @Param: [userApp]
    * @return: boolean
    */
    boolean inserUserApp(UserApp userApp);

    /**
    * @Description: 删除用户关注
    * @Param: [userApp]
    * @return: boolean
    */
    boolean deleteUserApp(UserApp userApp);

    /**
    * @Description: 根据用户id查询关注列表
    * @Param: [userId]
    * @return: java.util.List<top.cocoawork.model.UserApp>
    */
    List<UserApp> selectUserAppsByUserId(String userId);

    /**
    * @Description: 根据appid查询用户关注
    * @Param: [appId]
    * @return: java.util.List<top.cocoawork.model.UserApp>
    */
    List<UserApp> selectUserAppsByAppId(String appId);
}
