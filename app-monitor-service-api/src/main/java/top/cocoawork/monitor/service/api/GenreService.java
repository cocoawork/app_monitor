package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.dto.GenreDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface GenreService {

    GenreDto insert(@Valid @NotNull(message = "插入对象不能为空") GenreDto genre) throws Exception;

    boolean deleteById(@NotNull(message = "id不能为空") String appId);

    GenreDto update(@NotNull(message = "更新对象不能为空") GenreDto genre);

    GenreDto selectById(@NotNull(message = "id不能为空") String genreId);

}
