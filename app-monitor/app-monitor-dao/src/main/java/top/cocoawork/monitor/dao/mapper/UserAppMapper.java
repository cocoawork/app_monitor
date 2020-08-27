package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.UserApp;

import java.util.List;

@Component
@Mapper
public interface UserAppMapper extends BaseMapper<UserApp> {

    List<UserApp> selectByUserId(Long userId);

    List<UserApp> selectByAppId(String appId);
}
