package com.cocoawork.appstore.service.impl;

import com.cocoawork.appstore.entity.Media;
import com.cocoawork.appstore.mapper.MediaMapper;
import com.cocoawork.appstore.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;

public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public Integer addMedia(Media media) {
        return mediaMapper.addMedia(media);
    }
}
