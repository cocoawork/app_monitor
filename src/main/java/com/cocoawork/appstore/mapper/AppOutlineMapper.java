package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.AppOutline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppOutlineMapper {

    public Integer addAppRecoder(AppOutline appOutline);

    public AppOutline getOne();

    public List<AppOutline> getApps(@Param("countryCode") String countryCode, @Param("mediaType") String mediaType);

    public AppOutline getAppById(String id);

    public Integer deleteAppById(String id);
}
