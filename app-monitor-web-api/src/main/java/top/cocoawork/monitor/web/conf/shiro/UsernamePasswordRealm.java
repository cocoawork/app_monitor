package top.cocoawork.monitor.web.conf.shiro;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import top.cocoawork.monitor.web.conf.jwt.JwtToken;
import top.cocoawork.monitor.web.conf.jwt.JwtUtil;
import top.cocoawork.monitor.service.api.UserService;
import top.cocoawork.monitor.service.api.dto.UserDto;
import top.cocoawork.monitor.web.util.ApplicationContextHolder;

public class UsernamePasswordRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userRole = JwtUtil.decode4UserRole(principals.toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (null != userRole) {
            String[] roles = userRole.split(",");
            if (null != roles) {
                for (String roleName : roles) {
                    authorizationInfo.addRole(roleName);
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        UserDto user = null;
        try {
            Long userId = ((JwtToken) token).getUserId();
            UserService userService = ApplicationContextHolder.getBean(UserService.class);
            user = userService.selectByUserId(userId);
        }catch (JWTDecodeException e) {
            throw new AuthenticationException("token无效!");
        }

        if (null == user) {
            throw new AuthenticationException("授权失败!");
        }

        if (user.getState() != 1) {
            throw new AuthenticationException("账号被冻结!");
        }
        //将userid放到request中
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), token.toString());
    }

}
