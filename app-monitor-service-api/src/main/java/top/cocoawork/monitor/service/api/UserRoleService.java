package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.dto.UserRoleDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface UserRoleService {

    UserRoleDto insert(@Valid @NotNull(message = "插入对象不能为空") UserRoleDto userRole);

    UserRoleDto update(@NotNull(message = "更新对象不能为空")UserRoleDto userRole);

    boolean delete(@NotNull(message = "id不能为空") Long id);

}
