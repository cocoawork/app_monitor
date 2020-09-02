package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.Country;

import java.util.List;

@Component
@Mapper
public interface CountryMapper extends BaseMapper<Country> {

}
