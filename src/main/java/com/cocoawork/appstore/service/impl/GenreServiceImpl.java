package com.cocoawork.appstore.service.impl;

import com.cocoawork.appstore.entity.Genre;
import com.cocoawork.appstore.mapper.GenreMapper;
import com.cocoawork.appstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public Integer addGenre(Genre genre) {
        return genreMapper.addGenre(genre);
    }
}
