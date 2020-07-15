package top.cocoawork.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.model.AppInfo;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;
import top.cocoawork.response.WebResponseObject;
import top.cocoawork.service.AppInfoService;

@RequiresUser
@RestController
@RequestMapping("/appInfo")
public class AppInfoController {

    @Reference
    private AppInfoService appInfoService;

    @GetMapping("/{appId}")
    public IResponse getAppInfoByAppId(@PathVariable("/appId") String appId){
        AppInfo appInfo = appInfoService.selectAppInfoByAppId(appId);
        if (null != appInfo){
            return WebResponseObject.ok(appInfo);
        }else {
            return WebResponse.fail();
        }
    }


    @GetMapping("/{bundleId}")
    public IResponse getAppInfoByBundleId(@PathVariable("/bundleId") String bundleId){
        AppInfo appInfo = appInfoService.selectAppInfoByBundleId(bundleId);
        if (null != appInfo){
            return WebResponseObject.ok(appInfo);
        }else {
            return WebResponse.fail();
        }
    }

    @RequiresRoles("admin")
    @PostMapping("/add")
    public IResponse addAppInfo(@RequestBody AppInfo appinfo){
        boolean b = appInfoService.insertAppInfo(appinfo);
        return WebResponse.result(b);
    }

    @RequiresRoles("admin")
    @DeleteMapping("/{appId}")
    public IResponse deleteAppInfoByAppId(@PathVariable("appId") String appId){
        boolean b = appInfoService.deleteAppInfoByAppId(appId);
        return WebResponse.result(b);
    }

}
