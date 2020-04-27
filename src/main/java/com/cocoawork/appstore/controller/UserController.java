package com.cocoawork.appstore.controller;

import com.cocoawork.appstore.annotation.IgnoreToken;
import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import com.cocoawork.appstore.service.UserService;
import com.cocoawork.appstore.util.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @IgnoreToken
    @PostMapping("/login")
    public Response login(@RequestParam @NotNull String userName,
                          @RequestParam @NotNull String password) {

        User user = userService.login(userName, password);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);

        //生成token
        String token = JwtUtil.genreToken(user.getUid());
        Map ret = new HashMap();
        ret.put("token", token);
        return new ResponseData<>(ret);
    }

    @RequiresRoles("admin")
    @PostMapping("/logout")
    public Response lougout() {
        SecurityUtils.getSubject().logout();
        return Response.ok();
    }

}
