package com.cocoawork.appstore.config.shiro;

import com.cocoawork.appstore.config.Jwt.JWTToken;
import com.cocoawork.appstore.entity.Permission;
import com.cocoawork.appstore.entity.Role;
import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.entity.UserRole;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.exception.ExceptionEnum;
import com.cocoawork.appstore.service.UserRoleService;
import com.cocoawork.appstore.service.UserService;
import com.cocoawork.appstore.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountLoginRelam extends AuthorizingRealm {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String uid = JwtUtil.decodeUserId(principals.toString());

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
        }else {
            throw new CustomException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION);
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String uid = JwtUtil.decodeUserId(token.getPrincipal().toString());
        User user = userService.getUser(uid);
        if (null == user) {
            throw new AuthenticationException(ExceptionEnum.USER_LOGIN_ACCOUNT_NOT_EXIST.getMessage());
        }
        Boolean verify = JwtUtil.verify(token.getPrincipal().toString(), user.getPassword());
        if (!verify) {
            throw new AuthenticationException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION.getMessage());
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), token.toString());
    }
}
