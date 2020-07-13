package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.UserAppEntity;

@Component
@Mapper
public interface UserAppMapper extends BaseMapper<UserAppEntity> {
}
