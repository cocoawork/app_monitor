package top.cocoawork.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.cocoawork.model.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Override
    public boolean insertGenre(Genre genre) throws Exception {
        return false;
    }
    @Override
    public boolean deleteGenreByAppId(String appId) {
        return false;
    }

    @Override
    public boolean updateGenre(Genre genre) {
        return false;
    }

    @Override
    public Genre selectGenreByGenreId(String genreId) {
        return null;
    }

    @Override
    public List<Genre> selectGenresByPage(Integer pageIndex, Integer pageNum) {
        return null;
    }
}
