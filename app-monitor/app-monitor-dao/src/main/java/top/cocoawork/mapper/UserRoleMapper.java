package top.cocoawork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.cocoawork.entity.UserRoleEntity;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    UserRoleEntity selectUserRoleByUserId(String userId);

}
