package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.cocoawork.monitor.dao.mapper.UserRoleMapper;
import top.cocoawork.monitor.service.api.model.UserRoleDto;
import top.cocoawork.monitor.dao.entity.UserRole;
import top.cocoawork.monitor.service.api.UserRoleService;
import top.cocoawork.monitor.util.BeanUtil;

import javax.validation.constraints.NotNull;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean insertUserRole(@NotNull UserRoleDto userRole) {
        UserRole entity = new UserRole();
        BeanUtil.copyProperties(userRole, entity);
        int insert = userRoleMapper.insert(entity);
        return insert != 0;
    }

    @Override
    public boolean updateUserRole(@NotNull UserRoleDto userRole) {

        return false;
    }


    @Override
    public boolean deleteUserRole(@NotNull UserRoleDto userRole) {
        return false;
    }

    @Override
    public UserRoleDto selectUserRoleByUserId(String userId) {
        UserRole entity = userRoleMapper.selectUserRoleByUserId(userId);
        if (null != entity){
            UserRoleDto userRole = new UserRoleDto();
            BeanUtil.copyProperties(entity, userRole);
            return userRole;
        }
        return null;
    }
}
