package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.Feed;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {

    public Integer addFeed(Feed feed);
}
