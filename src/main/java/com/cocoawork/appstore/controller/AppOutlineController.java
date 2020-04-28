package com.cocoawork.appstore.controller;

import com.cocoawork.appstore.entity.AppOutline;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import com.cocoawork.appstore.service.AppOutlineService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/app/outline")
public class AppOutlineController {

    @Autowired
    private AppOutlineService appOutlineService;

    @RequiresUser
    @GetMapping("/one")
    public Response getApp() {
        AppOutline one = appOutlineService.getOne();
        return new ResponseData(one);
    }

    @RequiresUser
    @GetMapping("/get")
    public Response getApps(@RequestParam(defaultValue = "eg") String countryCode,
                            @RequestParam(defaultValue = "ios-apps") String mediaType,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            @RequestParam(defaultValue = "1") Integer pageNum) {
        List<AppOutline> apps = appOutlineService.getApps(countryCode, mediaType, pageNum, pageSize);
        return new ResponseData<>(apps);
    }

    @RequiresUser
    @GetMapping("/{id}")
    public Response getAppById(@PathVariable @NotNull String id) {
        AppOutline appOutline = appOutlineService.getAppById(id);
        return new ResponseData<>(appOutline);
    }

    @RequiresRoles("admin")
    @DeleteMapping("/delete/{id}")
    public Response deleteAppById(@PathVariable String id) {
        Integer ret = appOutlineService.deleteAppById(id);
        if (ret > 0){
            return Response.ok();
        }else {
            return Response.fail();
        }
    }

}
