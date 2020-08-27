package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.monitor.dao.entity.Genre;
import top.cocoawork.monitor.dao.mapper.GenreMapper;
import top.cocoawork.monitor.service.api.model.GenreDto;
import top.cocoawork.monitor.service.api.GenreService;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, GenreDto> implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public GenreDto insert(@NotNull GenreDto genreDto) throws Exception {
        Genre genre = dto2d(genreDto);
        genreMapper.insert(genre);
        return d2dto(genre);
    }
    @Override
    public boolean deleteById(@NotNull String appId) {
        return genreMapper.deleteById(appId) != 0;
    }

    @Override
    public GenreDto update(@NotNull GenreDto genreDto) {
        Genre genre = dto2d(genreDto);
        genreMapper.updateById(genre);
        return d2dto(genre);
    }

    @Override
    public GenreDto selectById(@NotNull String genreId) {
        return d2dto(genreMapper.selectById(genreId));
    }

}
