package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.UserEntity;

@Component
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
