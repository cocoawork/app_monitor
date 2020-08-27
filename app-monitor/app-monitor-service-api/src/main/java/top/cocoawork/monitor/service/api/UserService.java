package top.cocoawork.monitor.service.api;

import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.service.api.model.UserDto;

public interface UserService {

    /**
    * @Description: 新增用户
    * @Param: [user]
    * @return: boolean
    */
    boolean insert(UserDto user) throws ServiceException;

    /**
    * @Description: 更新用户信息
    * @Param: [user]
    * @return: boolean
    */
    boolean update(UserDto user);

    /**
    * @Description: 根据id删除用户
    * @Param: [id]
    * @return: boolean
    */
    boolean deleteById(String id);

    /**
    * @Description: 根据用户名密码查找用户
    * @Param: [username, password]
    * @return: top.cocoawork.model.User
    */
    UserDto loginByUsernameAndPasword(String username, String password) throws ServiceException;

    /**
    * @Description: 根据userID查询user
    * @Param: [userId]
    * @return: top.cocoawork.model.User
    */
    UserDto selectByUserId(String userId);

    /**
    * @Description: 根据用户名查找用户
    * @Param: 用户名
    * @return: top.cocoawork.model.User
    */
    UserDto selectByUserName(String userName);

}
