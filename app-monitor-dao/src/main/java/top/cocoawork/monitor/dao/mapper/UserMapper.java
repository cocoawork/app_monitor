package top.cocoawork.monitor.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.cocoawork.monitor.dao.entity.User;

import java.io.Serializable;
import java.util.List;

@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Override
    User selectById(Serializable id);

    List<User> selectByUsername(String username);

    List<User> selectByEmail(String email);

    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
