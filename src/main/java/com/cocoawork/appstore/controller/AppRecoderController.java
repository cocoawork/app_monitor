package com.cocoawork.appstore.controller;

import com.cocoawork.appstore.entity.AppRecoder;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import com.cocoawork.appstore.service.AppRecoderService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppRecoderController {

    @Autowired
    private AppRecoderService appRecoderService;

    @RequiresUser
    @GetMapping("/getOne")
    public Response getApp() {
        AppRecoder one = appRecoderService.getOne();
        return new ResponseData(one);
    }

}
