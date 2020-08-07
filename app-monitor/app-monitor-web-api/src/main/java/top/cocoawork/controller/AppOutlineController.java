package top.cocoawork.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import top.cocoawork.model.AppOutline;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;
import top.cocoawork.response.WebResponseObject;
import top.cocoawork.service.AppOutlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequiresUser
@RestController
@RequestMapping("/appOutline")
public class AppOutlineController {

    @Reference
    private AppOutlineService appOutlineService;

    @GetMapping("/{appId}")
    public IResponse getAppOutline(@PathVariable("appId") String appId){
        AppOutline appOutline = appOutlineService.selectAppOutlineByAppId(appId);
        if (null != appOutline){
            return WebResponseObject.ok(appOutline);
        }else {
            return WebResponse.fail();
        }
    }

    @GetMapping
    public IResponse getAppOutline(@RequestParam(value = "pageIndex", defaultValue = "0")Integer pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "country",required = false) String country){
        List<AppOutline> appOutlines = appOutlineService.selectAppOutlinesByPage(country, pageIndex, pageSize);
        return WebResponseObject.ok(appOutlines);
    }


    @RequiresRoles("admin")
    @DeleteMapping("/{appId}")
    public IResponse deleteAppOutline(@PathVariable("appId") String appId){
        boolean b = appOutlineService.deleteAppOutlineByAppId(appId);
        return WebResponse.result(b);
    }

    @RequiresRoles("admin")
    @PostMapping("/add")
    public IResponse addAppOutline(@RequestBody AppOutline appOutline){
        boolean b = appOutlineService.insertAppOutline(appOutline);
        return WebResponse.result(b);
    }



}
