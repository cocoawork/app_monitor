package top.cocoawork.conf.shiro;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import top.cocoawork.conf.jwt.JwtToken;
import top.cocoawork.conf.jwt.JwtUtil;
import top.cocoawork.exception.CustomWebException;
import top.cocoawork.exception.ExceptionEnum;
import top.cocoawork.model.User;
import top.cocoawork.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class UsernamePasswordRealm extends AuthorizingRealm {

//    @Autowired
//    private UserRoleService userRoleService;

    @Reference
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String uid = JwtUtil.decodeUserId(principals.toString());
//
//        if (null != uid) {
//            UserRole userRole = userRoleService.getUserRole(uid);
//
//            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//
//            for (Role role: userRole.getRoles()) {
//                authorizationInfo.addRole(role.getRoleName());
//                for (Permission permission: role.getPermissions()) {
//                    authorizationInfo.addStringPermission(permission.getPerAuth());
//                }
//            }
//            return authorizationInfo;
//        }else {
//            throw new CustomException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION);
//        }
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String uid = JwtUtil.decodeUserId(token.getPrincipal().toString());
//        User user = userService.selectUserByUserId(uid);
//        if (null == user) {
//            throw new CustomWebException(ExceptionEnum.USER_LOGIN_EXCEPTION);
//        }
//        Boolean verify = JwtUtil.verify(token.getPrincipal().toString(), user.getPassword());
//        if (!verify) {
//            throw new CustomWebException(ExceptionEnum.USER_LOGIN_EXCEPTION);
//        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), token.toString());
    }
}
