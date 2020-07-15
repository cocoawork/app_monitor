package top.cocoawork.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import top.cocoawork.model.Genre;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Override
    public boolean insertGenre(@NotNull Genre genre) throws Exception {
        return false;
    }
    @Override
    public boolean deleteGenreByAppId(@NotNull String appId) {
        return false;
    }

    @Override
    public boolean updateGenre(@NotNull Genre genre) {
        return false;
    }

    @Override
    public Genre selectGenreByGenreId(@NotNull String genreId) {
        return null;
    }

    @Override
    public List<Genre> selectGenresByPage(Integer pageIndex, @NotNull Integer pageSize) {
        return null;
    }
}
