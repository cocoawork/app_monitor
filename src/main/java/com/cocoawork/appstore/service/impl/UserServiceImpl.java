package com.cocoawork.appstore.service.impl;

import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.exception.ExceptionEnum;
import com.cocoawork.appstore.mapper.UserMapper;
import com.cocoawork.appstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public User getUser(String userName, String password) {

        List<User> users = userMapper.getUser(userName, password);
        if (users.size() == 0) {
            throw new CustomException(ExceptionEnum.USER_LOGIN_ACCOUNT_NOT_EXIST);
        }

        if (users.size() != 1) {
            throw new CustomException(ExceptionEnum.USER_LOGIN_ACCOUNT_MULTI);
        }

        return users.get(0);
    }

    @Override
    public User getUser(String uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public User login(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            throw new CustomException(ExceptionEnum.USER_LOGIN_FAILED);
        }
        List<User> users = userMapper.getUser(userName, null);
        if (users.size() > 1) {
            throw new CustomException(ExceptionEnum.USER_LOGIN_ACCOUNT_MULTI);
        }
        if (users.size() == 0) {
            throw new CustomException(ExceptionEnum.USER_LOGIN_ACCOUNT_NOT_EXIST);
        }
        User user = users.get(0);

        if (!password.equals(user.getPassword())) {
            throw new CustomException(ExceptionEnum.USER_LOGIN_PASSWORD_ERROR);
        }

        return user;
    }

}
