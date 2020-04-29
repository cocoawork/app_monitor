package com.cocoawork.appstore.controller;

import com.cocoawork.appstore.entity.AppInfo;
import com.cocoawork.appstore.entity.AppOutline;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import com.cocoawork.appstore.service.AppService;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiresUser
@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/one")
    public Response getApp() {
        AppOutline one = appService.getOneAppOutline();
        return new ResponseData(one);
    }

    @GetMapping("/get")
    public Response getApps(@RequestParam(defaultValue = "eg") String countryCode,
                            @RequestParam(defaultValue = "ios-apps") String mediaType,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "1") Integer pageNum) {
        List<AppOutline> apps = appService.getAppOutlines(countryCode, mediaType, pageNum, pageSize);
        return new ResponseData<>(apps);
    }

    @GetMapping("/{id}")
    public Response getAppById(@PathVariable @NotNull String id) {
        AppOutline appOutline = appService.getAppOutlineById(id);
        return new ResponseData<>(appOutline);
    }

    @RequiresRoles("admin")
    @DeleteMapping("/delete/{id}")
    public Response deleteAppById(@PathVariable String id) {
        Integer ret = appService.deleteAppById(id);
        if (ret > 0){
            return Response.ok();
        }else {
            return Response.fail();
        }
    }

    @GetMapping("/detail/{id}")
    public Response getAppDetailById(@PathVariable("id") String id) {
        AppInfo appinfo = appService.getAppInfoById(id);
        return new ResponseData<>(appinfo);
    }

}
