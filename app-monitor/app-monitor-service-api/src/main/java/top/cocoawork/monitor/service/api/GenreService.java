package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.GenreDto;

import java.util.List;

public interface GenreService {

    boolean insertGenre(GenreDto genre) throws Exception;

    boolean deleteGenreByAppId(String appId);

    boolean updateGenre(GenreDto genre);

    GenreDto selectGenreByGenreId(String genreId);

    List<GenreDto> selectGenresByPage(Integer pageIndex, Integer pageNum);


}
