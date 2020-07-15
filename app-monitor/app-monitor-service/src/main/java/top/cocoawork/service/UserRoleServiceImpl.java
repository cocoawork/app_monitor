package top.cocoawork.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.entity.UserRoleEntity;
import top.cocoawork.mapper.UserRoleMapper;
import top.cocoawork.model.UserRole;
import top.cocoawork.util.BeanUtil;

import javax.validation.constraints.NotNull;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean insertUserRole(@NotNull UserRole userRole) {
        UserRoleEntity entity = new UserRoleEntity();
        BeanUtil.convert(userRole, entity);
        int insert = userRoleMapper.insert(entity);
        return insert != 0;
    }

    @Override
    public boolean updateUserRole(@NotNull UserRole userRole) {

        return false;
    }

    @Override
    public boolean deleteUserRole(@NotNull UserRole userRole) {
        return false;
    }

    @Override
    public UserRole selectUserRoleByUserId(String userId) {
        UserRoleEntity entity = userRoleMapper.selectUserRoleByUserId(userId);
        if (null != entity){
            UserRole userRole = new UserRole();
            BeanUtil.convert(entity, userRole);
            return userRole;
        }
        return null;
    }
}
