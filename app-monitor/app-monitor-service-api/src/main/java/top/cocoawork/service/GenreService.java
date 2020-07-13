package top.cocoawork.service;

import top.cocoawork.model.Genre;

import java.util.List;

public interface GenreService {

    boolean insertGenre(Genre genre) throws Exception;

    boolean deleteGenreByAppId(String appId);

    boolean updateGenre(Genre genre);

    Genre selectGenreByGenreId(String genreId);

    List<Genre> selectGenresByPage(Integer pageIndex, Integer pageNum);


}
