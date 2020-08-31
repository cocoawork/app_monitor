package top.cocoawork.monitor.web.controller;

import com.sun.deploy.association.utility.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.web.conf.jwt.JwtUtil;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.web.exception.WebException;
import top.cocoawork.monitor.service.api.dto.UserDto;
import top.cocoawork.monitor.service.api.dto.UserRoleDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;
import top.cocoawork.monitor.service.api.UserService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Api(tags = "用户", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Reference
    private UserService userService;

    @RequiresGuest
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public IResponse<String> login(@RequestParam("username") @NotBlank(message = "用户名不能为空") String username,
                                   @RequestParam("password") @NotBlank(message = "密码不能为空") String password) {
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
    public IResponse regist(@RequestBody @Valid UserDto userDto) {
        try {
            userService.insert(userDto);
        }catch (ServiceException e) {
            throw new WebException(e.getCode(), e.getMsg());
        }
        return WebResponse.ok();
    }

    @RequiresUser
    @ApiOperation("用户修改密码")
    @PostMapping("/restPwd")
    public IResponse resetPwd(@RequestParam("oldPassword") @NotBlank(message = "旧密码不能为空") String oldPassword,
                              @RequestParam("newPassword") @NotBlank(message = "新密码不能为空") String newPassword) {

        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        if (principals == null){
            throw new WebException(102,"用户不存在");
        }
        String token = principals.toString();
        Long userId = JwtUtil.decode4UserId(token);

        UserDto user = userService.selectByUserId(userId);
        //用户不存在
        if (null == user) {
            throw new WebException(102,"用户不存在");
        }
        //旧密码验证错误
        if (!oldPassword.equals(user.getPassword())){
            throw new WebException(109,"旧密码验证失败");
        }

        if (oldPassword.equals(newPassword)) {
            throw new WebException(110,"新密码不能和旧密码一致！");
        }

        user.setPassword(newPassword);
        userService.update(user);
        return WebResponse.ok();
    }

    @RequiresGuest
    @ApiOperation(value = "授权失败跳转",hidden = true)
    @GetMapping("/unAuthorized")
    public IResponse unauthorized() {
        throw new AuthenticationException("Unauthorized");
    }

}
