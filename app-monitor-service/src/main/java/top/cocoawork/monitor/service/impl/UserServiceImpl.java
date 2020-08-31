package top.cocoawork.monitor.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.UserMapper;
import top.cocoawork.monitor.dao.entity.User;
import top.cocoawork.monitor.dao.entity.UserRole;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.service.api.exception.ExceptionEnum;
import top.cocoawork.monitor.dao.mapper.UserRoleMapper;
import top.cocoawork.monitor.service.api.dto.UserDto;
import top.cocoawork.monitor.service.api.UserService;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Validated
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDto insert(@Valid @NotNull(message = "插入对象不能为空") UserDto userDto) throws ServiceException {
        //查询用用户名，邮箱是否重复
        String username = userDto.getUsername();
        if (!userMapper.selectByUsername(username).isEmpty()) {
            throw new ServiceException(ExceptionEnum.USER_REGIST_EXCEPTION);
        }

        String email = userDto.getEmail();
        if (!userMapper.selectByEmail(email).isEmpty()) {
            throw new ServiceException(ExceptionEnum.USER_REGIST_EXCEPTION);
        }

        User user = dto2d(userDto);
        //新用户设置默认角色为 user
        UserRole defaultRole = new UserRole();
        defaultRole.setRole("user");
        defaultRole.setTag("普通用户");
        user.getRoles().clear();
        user.getRoles().add(defaultRole);

        Set<UserRole> roles = user.getRoles();
        if (null != roles){
            Set<Long> roleIds = new HashSet<>(roles.size());
            for (UserRole role : roles) {
                //插入角色之前先查询是否已经存在对应角色
                HashMap<String, Object> map = new HashMap<>(1);
                map.put("role", role.getRole());
                List<UserRole> roleList = userRoleMapper.selectByMap(map);
                if (null != roleList && !roleList.isEmpty()) {
                    role.setId(roleList.get(0).getId());
                }else {
                    userRoleMapper.insert(role);
                }
                roleIds.add(role.getId());
            }
            String roleIdString = StringUtils.join(roleIds, ",");
            user.setRole(roleIdString);
        }
        userMapper.insert(user);
        return d2dto(user);
    }

    @Override
    public UserDto update(@NotNull(message = "更新对象不能为空") UserDto userDto) {
        User user = dto2d(userDto);
        userMapper.updateById(user);
        return d2dto(user);
    }

    @Override
    public boolean deleteById(@NotNull(message = "user id不能为空") String id) {
        return userMapper.deleteById(id) != 0;
    }


    @Override
    @Validated
    public UserDto loginByUsernameAndPasword(@NotNull(message = "用户名不能为空") String username,
                                             @NotNull(message = "密码不能为空")  String password) throws ServiceException {
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (null == user) {
            throw new ServiceException(ExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }
        return d2dto(user);
    }

    @Override
    public UserDto selectByUserId(@NotNull(message = "user id不能为空") Long userId) {
        User user = userMapper.selectById(userId);
        if (null != user) {
            return d2dto(user);
        }
        return null;
    }


    @Override
    public UserDto selectByUserName(@NotNull(message = "用户名不能为空") String userName){
        List<User> users = userMapper.selectByUsername(userName);
        if (null != users && !users.isEmpty()) {
            User user = users.get(0);
            return d2dto(user);
        }
        return null;
    }
}
