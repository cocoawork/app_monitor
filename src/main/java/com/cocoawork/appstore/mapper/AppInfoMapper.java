package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.AppInfo;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppInfoMapper {

    public Integer addAppInfo(AppInfo appInfo);

    public AppInfo getAppInfoById(@Param("appId") String appId);

    public Integer deleteAppInfoById(@Param("appId") String appId);

}
