package top.cocoawork.service;

import top.cocoawork.model.UserRole;

public interface UserRoleService {

    boolean insertUserRole(UserRole userRole);

    boolean updateUserRole(UserRole userRole);

    boolean deleteUserRole(UserRole userRole);

    UserRole selectUserRoleByUserId(String userId);

}
