package top.cocoawork.monitor.web.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.service.api.model.AppOutlineDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;
import top.cocoawork.monitor.web.response.WebResponseObject;
import top.cocoawork.monitor.service.api.AppOutlineService;

import java.util.List;

@RequiresUser
@RestController
@RequestMapping("/appOutline")
public class AppOutlineController {

    @Reference
    private AppOutlineService appOutlineService;

    @GetMapping("/{appId}")
    public IResponse getAppOutline(@PathVariable("appId") String appId){
        AppOutlineDto appOutline = appOutlineService.selectById(appId);
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
        List<AppOutlineDto> appOutlines = appOutlineService.selectByPage(country, pageIndex, pageSize);
        return WebResponseObject.ok(appOutlines);
    }


    @RequiresRoles("admin")
    @DeleteMapping("/{appId}")
    public IResponse deleteAppOutline(@PathVariable("appId") String appId){
        boolean b = appOutlineService.deleteById(appId);
        return WebResponse.result(b);
    }

    @RequiresRoles("admin")
    @PostMapping("/add")
    public IResponse addAppOutline(@RequestBody AppOutlineDto appOutline){
        appOutlineService.insert(appOutline);
        return WebResponse.result(true);
    }



}
