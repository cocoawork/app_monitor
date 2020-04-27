package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.Media;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MediaMapper {

    public Integer addMedia(Media media);

}
