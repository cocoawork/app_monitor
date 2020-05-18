package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {

    Integer addOrUpdate(UserRole userRole);

    UserRole getUserRoleById(@Param("id") String userId);

}
