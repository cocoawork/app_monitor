package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    public Integer addRole(Role role);

}
