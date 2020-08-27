package top.cocoawork.monitor.web.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.service.api.AppInfoService;
import top.cocoawork.monitor.service.api.model.AppInfoDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;
import top.cocoawork.monitor.web.response.WebResponseObject;

@RequiresUser
@RestController
@RequestMapping("/appInfo")
public class AppInfoController {

    @Reference
    private AppInfoService appInfoService;

    @GetMapping("/{appId}")
    public IResponse getAppInfoByAppId(@PathVariable("/appId") String appId){
        AppInfoDto appInfo = appInfoService.selectById(appId);
        if (null != appInfo){
            return WebResponseObject.ok(appInfo);
        }else {
            return WebResponse.fail();
        }
    }


    @GetMapping("/{bundleId}")
    public IResponse getAppInfoByBundleId(@PathVariable("/bundleId") String bundleId){
        AppInfoDto appInfo = appInfoService.selectByBundleId(bundleId);
        if (null != appInfo){
            return WebResponseObject.ok(appInfo);
        }else {
            return WebResponse.fail();
        }
    }

    @RequiresRoles("admin")
    @PostMapping("/add")
    public IResponse addAppInfo(@RequestBody AppInfoDto appinfo){
        boolean b = appInfoService.insert(appinfo);
        return WebResponse.result(b);
    }

    @RequiresRoles("admin")
    @DeleteMapping("/{appId}")
    public IResponse deleteAppInfoByAppId(@PathVariable("appId") String appId){
        boolean b = appInfoService.deleteById(appId);
        return WebResponse.result(b);
    }

}
