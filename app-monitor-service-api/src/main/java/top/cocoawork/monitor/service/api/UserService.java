package top.cocoawork.monitor.service.api;

import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.service.api.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserService {

    /**
    * @Description: 新增用户
    * @Param: [user]
    * @return: boolean
    */
    UserDto insert(@Valid @NotNull(message = "插入对象不能为空") UserDto user) throws ServiceException;

    /**
    * @Description: 更新用户信息
    * @Param: [user]
    * @return: boolean
    */
    UserDto update(@NotNull(message = "更新对象不能为空") UserDto user);

    /**
    * @Description: 根据id删除用户
    * @Param: [id]
    * @return: boolean
    */
    boolean deleteById(@NotNull(message = "user id不能为空")String id);

    /**
    * @Description: 根据用户名密码查找用户
    * @Param: [username, password]
    * @return: top.cocoawork.model.User
    */
    UserDto loginByUsernameAndPasword(@NotNull(message = "用户名不能为空") String username,
                                      @NotNull(message = "密码不能为空")  String password) throws ServiceException;

    /**
    * @Description: 根据userID查询user
    * @Param: [userId]
    * @return: top.cocoawork.model.User
    */
    UserDto selectByUserId(@NotNull(message = "user id不能为空")Long userId);

    /**
    * @Description: 根据用户名查找用户
    * @Param: 用户名
    * @return: top.cocoawork.model.User
    */
    UserDto selectByUserName(@NotNull(message = "用户名不能为空") String userName);

}
