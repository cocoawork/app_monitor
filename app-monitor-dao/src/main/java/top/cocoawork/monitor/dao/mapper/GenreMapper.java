package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.Genre;

@Component
@Mapper
public interface GenreMapper extends BaseMapper<Genre> {

    @Select("select * from t_genre where name = #{name} and url = #{url} and is_delete = 0")
    Genre selectByNameAndUrl(@Param("name") String name, @Param("url") String url);

}
