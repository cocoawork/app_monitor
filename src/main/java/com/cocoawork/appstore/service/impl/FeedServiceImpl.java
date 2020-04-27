package com.cocoawork.appstore.service.impl;

import com.cocoawork.appstore.entity.Feed;
import com.cocoawork.appstore.mapper.FeedMapper;
import com.cocoawork.appstore.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedMapper feedMapper;

    @Override
    public Integer addFeed(Feed feed) {
        return feedMapper.addFeed(feed);
    }
}
