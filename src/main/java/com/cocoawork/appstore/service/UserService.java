package com.cocoawork.appstore.service;

import com.cocoawork.appstore.entity.User;

public interface UserService {

    public Integer addUser(User user);

    public User getUser(String userName, String password);

    public User login(String userName, String password);

}
