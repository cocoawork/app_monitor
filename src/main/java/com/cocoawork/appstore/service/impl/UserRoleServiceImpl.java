package com.cocoawork.appstore.service.impl;

import com.cocoawork.appstore.entity.UserRole;
import com.cocoawork.appstore.mapper.UserRoleMapper;
import com.cocoawork.appstore.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole getUserRole(String uid) {
        return userRoleMapper.getUserRoleById(uid);
    }
}
