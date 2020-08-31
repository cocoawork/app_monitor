package top.cocoawork.monitor.web.controller;

import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Api(tags = "用户", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference(retries = 0)
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public IResponse<String> login(@ApiParam(value = "username", required = true) @RequestParam("username") String username,
                                   @ApiParam(value = "password", required = true) @RequestParam("password") String password) {
        try {
            UserDto user = userService.loginByUsernameAndPasword(username, password);
            Set<UserRoleDto> roles = user.getRoles();
            String roleString = roles.stream().map(UserRoleDto::getRole).collect(Collectors.joining(","));
            String token = JwtUtil.genreToken(user.getId(), user.getUsername(), roleString);
            return WebResponse.ok(token);
        }catch (ServiceException e) {
            throw new WebException(e.getCode(), e.getMsg());
        }
    }

    @ApiOperation("用户注册")
    @PostMapping("/regist")
    public IResponse regist(@RequestBody UserDto userDto) {
        try {
            userService.insert(userDto);
        }catch (ServiceException e) {
            throw new WebException(e.getCode(), e.getMsg());
        }
        return WebResponse.ok();
    }

    @ApiOperation("用户修改密码")
    @PostMapping("/restPwd")
    public IResponse resetPwd(@RequestParam("username") String username,
                              @RequestParam("oldPassword") String oldPassword,
                              @RequestParam("newPassword") String newPassword) {

        UserDto user = userService.selectByUserName(username);
        //用户不存在
        if (null == user) {
            throw new WebException(102,"用户不存在");
        }
        //旧密码验证错误
        if (!oldPassword.equals(user.getPassword())){
            throw new WebException(109,"旧密码验证失败");
        }
        user.setPassword(newPassword);
        userService.update(user);
        return WebResponse.ok();
    }


    @ApiOperation(value = "授权失败跳转",hidden = true)
    @GetMapping("/unAuthorized")
    public IResponse unauthorized() {
        throw new AuthenticationException("UnAuthorized");
    }

}
