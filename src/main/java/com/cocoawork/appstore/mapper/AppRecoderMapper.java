package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.AppRecoder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppRecoderMapper {

    public Integer addAppRecoder(AppRecoder appRecoder);

    public AppRecoder getOne();
}
