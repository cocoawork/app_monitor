package com.cocoawork.appstore.config.shiro;

import com.cocoawork.appstore.entity.Permission;
import com.cocoawork.appstore.entity.Role;
import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.entity.UserRole;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.exception.UserException;
import com.cocoawork.appstore.service.UserRoleService;
import com.cocoawork.appstore.service.UserService;
import com.cocoawork.appstore.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;


public class UserAccountRelam extends AuthorizingRealm {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private HttpServletRequest request;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String uid = (String) request.getAttribute("UID");
        if (null != uid) {
            UserRole userRole = userRoleService.getUserRole(uid);

            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

            for (Role role: userRole.getRoles()) {
                authorizationInfo.addRole(role.getRoleName());
                for (Permission permission: role.getPermissions()) {
                    authorizationInfo.addStringPermission(permission.getPerAuth());
                }
            }
            return authorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String userPwd = new String((char[]) token.getCredentials());
        return new SimpleAuthenticationInfo(userName, userPwd, userName);
    }
}
