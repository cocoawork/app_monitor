package top.cocoawork.conf.shiro;

import com.auth0.jwt.exceptions.JWTVerificationException;
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
import top.cocoawork.conf.jwt.JwtToken;
import top.cocoawork.conf.jwt.JwtUtil;
import top.cocoawork.constant.Constant;
import top.cocoawork.model.User;
import top.cocoawork.model.UserRole;
import top.cocoawork.service.UserRoleService;
import top.cocoawork.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class UsernamePasswordRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String uid = JwtUtil.decode4UserId(principals.toString());
        UserRole userRole = userRoleService.selectUserRoleByUserId(uid);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (null != userRole) {
            authorizationInfo.addRole(userRole.getUserRole());
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;
        String userId = null;
        try {
            JwtUtil.verify(jwtToken.getToken());
            userId= JwtUtil.decode4UserId(jwtToken.getToken());
        } catch (JWTVerificationException e) {
            throw new AuthenticationException("token无效");
        }
        User user = userService.selectUserByUserId(userId);
        if (null == user) {
            throw new AuthenticationException("授权失败");
        }
        //将userid放到request中
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        request.setAttribute(Constant.REQUEST_HEADER_UID_KEY, userId);
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), token.toString());
    }

}
