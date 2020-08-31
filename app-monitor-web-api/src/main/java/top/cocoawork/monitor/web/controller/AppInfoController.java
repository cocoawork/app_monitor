package top.cocoawork.monitor.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.service.api.AppInfoService;
import top.cocoawork.monitor.service.api.model.AppInfoDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;

@RequiresRoles(ApplicationConstant.USER_ROLE_USER)
@RestController
@RequestMapping("/appInfo")
@Api(tags = "app详细信息", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppInfoController {

    @Reference
    private AppInfoService appInfoService;

    @ApiOperation(value = "根据id获取app详细信息")
    @GetMapping("/{appId}")
    public IResponse<AppInfoDto> getAppInfoByAppId(@ApiParam(name = "appid", required = true) @PathVariable("appId") String appId){
        AppInfoDto appInfo = appInfoService.selectById(appId);
        return WebResponse.ok(appInfo);
    }

    @ApiOperation(value = "根据bundle id获取app详细信息")
    @GetMapping("/{bundleId}")
    public IResponse<AppInfoDto> getAppInfoByBundleId(@ApiParam(name = "bundle id", required = true) @PathVariable("bundleId") String bundleId){
        AppInfoDto appInfo = appInfoService.selectByBundleId(bundleId);
        return WebResponse.ok(appInfo);
    }

    @RequiresRoles(ApplicationConstant.USER_ROLE_ADMIN)
    @ApiOperation(value = "新增appInfo")
    @PostMapping("/add")
    public IResponse<AppInfoDto> addAppInfo(@RequestBody AppInfoDto appinfo){
        return WebResponse.ok(appInfoService.insert(appinfo));
    }

    @ApiOperation(value = "根据id删除appinfo")
    @RequiresRoles(ApplicationConstant.USER_ROLE_ADMIN)
    @DeleteMapping("/{appId}")
    public IResponse deleteAppInfoByAppId(@ApiParam(name = "appid", required = true)  @PathVariable("appId") String appId){
        boolean b = appInfoService.deleteById(appId);
        return WebResponse.result(b);
    }

}
