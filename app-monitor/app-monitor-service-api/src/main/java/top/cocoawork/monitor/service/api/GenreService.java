package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.GenreDto;

import java.util.List;

public interface GenreService {

    GenreDto insert(GenreDto genre) throws Exception;

    boolean deleteById(String appId);

    GenreDto update(GenreDto genre);

    GenreDto selectById(String genreId);

}
