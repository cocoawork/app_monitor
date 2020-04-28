package com.cocoawork.appstore.controller;

import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import com.cocoawork.appstore.service.UserService;
import com.cocoawork.appstore.util.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;


@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Response login(@RequestParam @NotNull String userName,
                          @RequestParam @NotNull String password) {

        User user = userService.login(userName, password);
        //生成token
        String token = JwtUtil.genreToken(user.getUid(), user.getUserName(), user.getPassword());
        return new ResponseData<>(token);
    }


}
