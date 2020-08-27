package top.cocoawork.monitor.service.api;


import top.cocoawork.monitor.service.api.model.UserRoleDto;

public interface UserRoleService {

    boolean insertUserRole(UserRoleDto userRole);

    boolean updateUserRole(UserRoleDto userRole);

    boolean deleteUserRole(UserRoleDto userRole);

    UserRoleDto selectUserRoleByUserId(String userId);

}
