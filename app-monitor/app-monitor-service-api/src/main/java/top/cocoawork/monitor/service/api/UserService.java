package top.cocoawork.monitor.service.api;

import top.cocoawork.monitor.service.api.exception.CustomServiceException;
import top.cocoawork.monitor.service.api.model.UserDto;

public interface UserService {

    /**
    * @Description: 新增用户
    * @Param: [user]
    * @return: boolean
    */
    boolean insertUser(UserDto user) throws CustomServiceException;

    /**
    * @Description: 更新用户信息
    * @Param: [user]
    * @return: boolean
    */
    boolean updateUser(UserDto user);

    /**
    * @Description: 根据id删除用户
    * @Param: [id]
    * @return: boolean
    */
    boolean deleteUserById(String id);

    /**
    * @Description: 根据用户名密码查找用户
    * @Param: [username, password]
    * @return: top.cocoawork.model.User
    */
    UserDto loginByUsernameAndPasword(String username, String password) throws CustomServiceException;

    /**
    * @Description: 根据userID查询user
    * @Param: [userId]
    * @return: top.cocoawork.model.User
    */
    UserDto selectUserByUserId(String userId);

    /**
    * @Description: 根据用户名查找用户
    * @Param: 用户名
    * @return: top.cocoawork.model.User
    */
    UserDto selectUserByUserName(String userName);

}
