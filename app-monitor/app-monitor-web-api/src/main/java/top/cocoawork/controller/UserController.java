package top.cocoawork.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.conf.jwt.JwtUtil;
import top.cocoawork.exception.CustomServiceException;
import top.cocoawork.exception.CustomWebException;
import top.cocoawork.exception.ExceptionEnum;
import top.cocoawork.model.User;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;
import top.cocoawork.response.WebResponseObject;
import top.cocoawork.service.UserService;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @PostMapping("/login")
    public IResponse login(@RequestParam("username") String userName,
                           @RequestParam("password") String password) {
        User user = null;
        try {
            user = userService.loginByUsernameAndPasword(userName, password);
        }catch (CustomServiceException e) {
            throw new CustomWebException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        if (null == user) {
            throw new CustomServiceException(ExceptionEnum.USER_LOGIN_EXCEPTION);
        }
        String token = JwtUtil.genreToken(user.getId(), user.getUsername(), user.getPassword());
        return WebResponseObject.ok(token);
    }

    @PostMapping("/regist")
    public IResponse regist(@RequestBody User user) {
        boolean ret = false;
        try {
            ret = userService.insertUser(user);
        }catch (CustomServiceException e) {
            throw new CustomWebException(ExceptionEnum.USER_REGIST_EXCEPTION);
        }
        if (ret) {
            return WebResponse.ok();
        }else {
            return WebResponse.fail();
        }
    }

    @PostMapping("/restPwd")
    public IResponse resetPwd(@RequestParam("userName") String userName,
                              @RequestParam("oldPassword") String oldPassword,
                              @RequestParam("newPassword") String newPassword) {
        User user = userService.selectUserByUserName(userName);
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

//    @GetMapping("/favour")
//    public IResponse getFavourList() {
//
//    }

}
