package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.entity.UserAppEntity;

import java.util.List;

@Component
@Mapper
public interface UserAppMapper extends BaseMapper<UserAppEntity> {

    List<UserAppEntity> selectUserAppsByUserId(String userId);

    List<UserAppEntity> selectUserAppsByAppId(String appId);
}
