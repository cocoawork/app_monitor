package top.cocoawork.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.entity.UserEntity;
import top.cocoawork.exception.CustomServiceException;
import top.cocoawork.exception.ExceptionEnum;
import top.cocoawork.mapper.UserMapper;
import top.cocoawork.model.User;
import top.cocoawork.util.BeanUtil;

import javax.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean insertUser(User user) throws CustomServiceException {
        UserEntity userEntity = new UserEntity();
        BeanUtil.convert(user, userEntity);
        try {
            userMapper.insert(userEntity);
        }catch (Exception e) {
            throw new CustomServiceException(ExceptionEnum.USER_REGIST_EXCEPTION);
        }
        user.setId(userEntity.getId());
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtil.convert(user, userEntity);
        return userMapper.updateById(userEntity) != 0;
    }

    @Override
    public boolean deleteUserById(String id) {
        return userMapper.deleteById(id) != 0;
    }

    @Override
    public User loginByUsernameAndPasword(String username, String password) throws CustomServiceException {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>().eq("username", username);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        if (null == userEntity) {
            throw new CustomServiceException(ExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }
        if (!userEntity.getPassword().equals(password)) {
            throw new CustomServiceException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        User user = new User();
        BeanUtil.convert(userEntity, user);
        return user;
    }

    @Override
    public User selectUserByUserId(@NotNull String userId) {
        UserEntity userEntity = userMapper.selectById(userId);
        if (null != userEntity) {
            User user = new User();
            BeanUtil.convert(userEntity, user);
            return user;
        }
        return null;
    }


    @Override
    public User selectUserByUserName(@NotNull String userName){
        QueryWrapper<UserEntity> qw = new QueryWrapper<UserEntity>().eq("user_name", userName);
        UserEntity userEntity = userMapper.selectOne(qw);
        if (null != userEntity) {
            User user = new User();
            BeanUtil.convert(userEntity, user);
            return user;
        }
        return null;
    }
}
