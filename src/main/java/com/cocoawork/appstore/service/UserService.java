package com.cocoawork.appstore.service;

import com.cocoawork.appstore.entity.User;

public interface UserService {
    Integer addUser(User user);

    User getUser(String userName, String password);

    User getUser(String uid);

    User login(String userName, String password);


}
