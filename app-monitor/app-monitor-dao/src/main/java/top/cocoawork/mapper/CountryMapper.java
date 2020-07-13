package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.CountryEntity;

@Component
@Mapper
public interface CountryMapper extends BaseMapper<CountryEntity> {
}
