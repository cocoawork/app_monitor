package top.cocoawork.monitor.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.monitor.dao.mapper.UserRoleMapper;
import top.cocoawork.monitor.service.api.dto.UserRoleDto;
import top.cocoawork.monitor.dao.entity.UserRole;
import top.cocoawork.monitor.service.api.UserRoleService;
import top.cocoawork.monitor.service.impl.base.BaseServiceImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleDto> implements UserRoleService {

    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRoleDto insert(@Valid @NotNull(message = "插入对象不能为空") UserRoleDto userRoleDto) {
        UserRole userRole = dto2d(userRoleDto);
        userRoleMapper.insert(userRole);
        return d2dto(userRole);
    }

    @Override
    public UserRoleDto update(@NotNull(message = "更新对象不能为空") UserRoleDto userRoleDto) {
        UserRole userRole = dto2d(userRoleDto);
        userRoleMapper.updateById(userRole);
        return d2dto(userRole);
    }


    @Override
    public boolean delete(@NotNull(message = "id不能为空") Long id) {
        return userRoleMapper.deleteById(id) != 0;
    }

}
