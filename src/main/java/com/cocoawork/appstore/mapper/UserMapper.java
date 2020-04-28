package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    public Integer addUser(User user);

    public List<User> getUser(@Param("userName") String userName, @Param("password") String password);

    public User getUserById(String uid);
}
