package top.cocoawork.monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.cocoawork.monitor.dao.mapper.UserMapper;
import top.cocoawork.monitor.dao.entity.User;
import top.cocoawork.monitor.dao.entity.UserRole;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.service.api.exception.ExceptionEnum;
import top.cocoawork.monitor.dao.mapper.UserRoleMapper;
import top.cocoawork.monitor.service.api.model.UserDto;
import top.cocoawork.monitor.service.api.UserService;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(@NotNull UserDto user) throws ServiceException {
        User userEntity = new User();
        BeanUtil.copyProperties(user, userEntity);
        try {
            userMapper.insert(userEntity);
            //为用户创建身份，默认为普通用户
            UserRole userRoleEntity = new UserRole();
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setUserRole("user");
            userRoleMapper.insert(userRoleEntity);

        }catch (Exception e) {
            throw new ServiceException(ExceptionEnum.USER_REGIST_EXCEPTION);
        }
        user.setId(userEntity.getId());
        return true;
    }

    @Override
    public boolean update(@NotNull UserDto user) {
        User userEntity = new User();
        BeanUtil.copyProperties(user, userEntity);
        return userMapper.updateById(userEntity) != 0;
    }

    @Override
    public boolean deleteById(@NotNull String id) {
        return userMapper.deleteById(id) != 0;
    }

    @Override
    public UserDto loginByUsernameAndPasword(@NotNull String username, @NotNull  String password) throws ServiceException {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        User userEntity = userMapper.selectOne(wrapper);
        if (null == userEntity) {
            throw new ServiceException(ExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }
        if (!userEntity.getPassword().equals(password)) {
            throw new ServiceException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        UserDto user = new UserDto();
        BeanUtil.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public UserDto selectByUserId(@NotNull String userId) {
        User userEntity = userMapper.selectById(userId);
        if (null != userEntity) {
            UserDto user = new UserDto();
            BeanUtil.copyProperties(userEntity, user);
            return user;
        }
        return null;
    }


    @Override
    public UserDto selectByUserName(@NotNull String userName){
        QueryWrapper<User> qw = new QueryWrapper<User>().eq("user_name", userName);
        User userEntity = userMapper.selectOne(qw);
        if (null != userEntity) {
            UserDto user = new UserDto();
            BeanUtil.copyProperties(userEntity, user);
            return user;
        }
        return null;
    }
}
