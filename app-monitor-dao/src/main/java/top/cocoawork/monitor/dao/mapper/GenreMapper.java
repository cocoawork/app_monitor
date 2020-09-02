package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.Genre;

@Component
@Mapper
public interface GenreMapper extends BaseMapper<Genre> {

    Genre selectByNameAndUrl(@Param("name") String name, @Param("url") String url);

}
