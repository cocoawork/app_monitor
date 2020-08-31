package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.entity.Genre;
import top.cocoawork.monitor.dao.mapper.GenreMapper;
import top.cocoawork.monitor.service.api.dto.GenreDto;
import top.cocoawork.monitor.service.api.GenreService;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, GenreDto> implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Override
    public GenreDto insert(@Valid @NotNull(message = "插入对象不能为空") GenreDto genreDto) throws Exception {
        Genre genre = dto2d(genreDto);
        genreMapper.insert(genre);
        return d2dto(genre);
    }
    @Override
    public boolean deleteById(@NotNull(message = "id不能为空") String appId) {
        return genreMapper.deleteById(appId) != 0;
    }

    @Override
    public GenreDto update(@NotNull(message = "更新对象不能为空") GenreDto genreDto) {
        Genre genre = dto2d(genreDto);
        genreMapper.updateById(genre);
        return d2dto(genre);
    }

    @Override
    public GenreDto selectById(@NotNull(message = "id不能为空") String genreId) {
        return d2dto(genreMapper.selectById(genreId));
    }

}
