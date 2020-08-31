package top.cocoawork.monitor.web.conf.shiro;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.cocoawork.monitor.web.conf.jwt.JwtToken;
import top.cocoawork.monitor.web.conf.jwt.JwtUtil;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.service.api.UserService;
import top.cocoawork.monitor.service.api.model.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UsernamePasswordRealm extends AuthorizingRealm {

    @Reference
    private UserService userService;

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
            user = userService.selectByUserId(userId);
        }catch (JWTDecodeException e) {
            throw new AuthenticationException("token无效！");
        }
        if (null == user) {
            throw new AuthenticationException("授权失败！");
        }
        //将userid放到request中
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        request.setAttribute(ApplicationConstant.REQUEST_HEADER_UID_KEY, user.getId());
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), token.toString());
    }

}
