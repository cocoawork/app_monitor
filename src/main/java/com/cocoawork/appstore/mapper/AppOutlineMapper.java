package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.AppOutline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppOutlineMapper {

    public Integer addAppOutline(AppOutline appOutline);

    public AppOutline getOneAppOutline();

    public List<AppOutline> getAppOutlines(@Param("countryCode") String countryCode, @Param("mediaType") String mediaType);

    public AppOutline getAppOutlineById(String id);

    public Integer deleteAppOutlineById(String id);
}
