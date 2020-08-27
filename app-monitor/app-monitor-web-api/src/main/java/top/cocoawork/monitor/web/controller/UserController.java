package top.cocoawork.monitor.web.controller;

import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.web.conf.jwt.JwtUtil;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.web.exception.WebException;
import top.cocoawork.monitor.service.api.exception.ExceptionEnum;
import top.cocoawork.monitor.service.api.model.UserDto;
import top.cocoawork.monitor.service.api.model.UserRoleDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;
import top.cocoawork.monitor.service.api.UserRoleService;
import top.cocoawork.monitor.service.api.UserService;

import java.util.HashMap;

@RestController
public class UserController {

    @Reference
    private UserService userService;

    @Reference
    private UserRoleService userRoleService;

    @PostMapping("/user/login")
    public IResponse login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        UserDto user = null;
        try {
            user = userService.loginByUsernameAndPasword(username, password);
        }catch (ServiceException e) {
            throw new WebException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        if (null == user) {
            throw new ServiceException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        String token = JwtUtil.genreToken(user.getId(), user.getUsername(), user.getPassword());
        HashMap<String, String> tokenData = new HashMap<>(1);
        tokenData.put("token", token);
        return WebResponse.ok(tokenData);
    }

    @GlobalTransactional(timeoutMills = 3000, rollbackFor = Exception.class)
    @PostMapping("/user/regist")
    public IResponse regist(@RequestBody UserDto user) {
        boolean ret1 = userService.insert(user);
        UserRoleDto userRole = new UserRoleDto();
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
        UserDto user = userService.selectByUserName(username);
        //用户不存在
        if (null == user) {
            return WebResponse.fail();
        }
        //旧密码验证错误
        if (!oldPassword.equals(user.getPassword())){
            return WebResponse.fail();
        }
        user.setPassword(newPassword);
        userService.update(user);
        return WebResponse.ok();
    }

    @RequestMapping("/handlerUnAuthorized")
    public IResponse unauthorized() {
        throw new AuthenticationException("UnAuthorized");
    }




}
