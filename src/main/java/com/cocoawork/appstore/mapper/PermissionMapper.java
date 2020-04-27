package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionMapper {

    public Integer addPermission(Permission permission);

}
