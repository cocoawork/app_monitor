package top.cocoawork.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.conf.jwt.JwtUtil;
import top.cocoawork.constant.Constant;
import top.cocoawork.exception.CustomServiceException;
import top.cocoawork.exception.CustomWebException;
import top.cocoawork.exception.ExceptionEnum;
import top.cocoawork.model.User;
import top.cocoawork.model.UserApp;
import top.cocoawork.model.UserRole;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;
import top.cocoawork.response.WebResponseObject;
import top.cocoawork.service.UserAppService;
import top.cocoawork.service.UserRoleService;
import top.cocoawork.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Reference
    private UserService userService;

    @Reference
    private UserRoleService userRoleService;

    @PostMapping("/user/login")
    public IResponse login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        User user = null;
        try {
            user = userService.loginByUsernameAndPasword(username, password);
        }catch (CustomServiceException e) {
            throw new CustomWebException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        if (null == user) {
            throw new CustomServiceException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        String token = JwtUtil.genreToken(user.getId(), user.getUsername(), user.getPassword());
        HashMap<String, String> tokenData = new HashMap<>(1);
        tokenData.put("token", token);
        return WebResponseObject.ok(tokenData);
    }

    @GlobalTransactional(timeoutMills = 3000, rollbackFor = Exception.class)
    @PostMapping("/user/regist")
    public IResponse regist(@RequestBody User user) {
        boolean ret1 = userService.insertUser(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("user");
        boolean ret2 = userRoleService.insertUserRole(userRole);
        if (ret1 && ret2) {
            return WebResponse.ok();
        }else {
            return WebResponse.fail();
        }
    }

    @PostMapping("/user/restPwd")
    public IResponse resetPwd(@RequestParam("username") String username,
                              @RequestParam("oldPassword") String oldPassword,
                              @RequestParam("newPassword") String newPassword) {
        User user = userService.selectUserByUserName(username);
        //用户不存在
        if (null == user) {
            return WebResponse.fail();
        }
        //旧密码验证错误
        if (!oldPassword.equals(user.getPassword())){
            return WebResponse.fail();
        }
        user.setPassword(newPassword);
        userService.updateUser(user);
        return WebResponse.ok();
    }

    @RequestMapping("/handlerUnAuthorized")
    public IResponse unauthorized() {
        throw new AuthenticationException("UnAuthorized");
    }




}
