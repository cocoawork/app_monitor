package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import top.cocoawork.monitor.service.api.model.GenreDto;
import top.cocoawork.monitor.service.api.GenreService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Override
    public boolean insertGenre(@NotNull GenreDto genre) throws Exception {
        return false;
    }
    @Override
    public boolean deleteGenreByAppId(@NotNull String appId) {
        return false;
    }

    @Override
    public boolean updateGenre(@NotNull GenreDto genre) {
        return false;
    }

    @Override
    public GenreDto selectGenreByGenreId(@NotNull String genreId) {
        return null;
    }

    @Override
    public List<GenreDto> selectGenresByPage(Integer pageIndex, @NotNull Integer pageSize) {
        return null;
    }
}
