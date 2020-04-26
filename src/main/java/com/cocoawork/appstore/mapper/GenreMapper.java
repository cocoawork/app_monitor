package com.cocoawork.appstore.mapper;

import com.cocoawork.appstore.entity.Genre;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GenreMapper {

    public Integer addGenre(Genre genre);

}
