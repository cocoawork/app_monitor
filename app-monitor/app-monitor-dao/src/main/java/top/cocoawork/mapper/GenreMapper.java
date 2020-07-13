package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.GenreEntity;

@Component
@Mapper
public interface GenreMapper extends BaseMapper<GenreEntity> {


}
