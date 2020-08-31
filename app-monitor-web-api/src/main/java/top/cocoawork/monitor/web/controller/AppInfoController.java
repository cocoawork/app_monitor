package top.cocoawork.monitor.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.service.api.AppInfoService;
import top.cocoawork.monitor.service.api.dto.AppInfoDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RequiresUser
@RestController
@RequestMapping("/appInfo")
@Validated
@Api(tags = "app详细信息", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppInfoController {

    @Reference
    private AppInfoService appInfoService;

    @ApiOperation(value = "根据id获取app详细信息")
    @GetMapping("/{appId}")
    public IResponse<AppInfoDto> getAppInfoByAppId(@PathVariable("appId") @NotBlank(message = "appid不能为空") String appId){
        AppInfoDto appInfo = appInfoService.selectById(appId);
        return WebResponse.ok(appInfo);
    }

    @ApiOperation(value = "根据bundle id获取app详细信息")
    @GetMapping("/bundleId/{bundleId}")
    public IResponse<AppInfoDto> getAppInfoByBundleId(@PathVariable("bundleId")@NotBlank(message = "bundleid不能为空") String bundleId){
        AppInfoDto appInfo = appInfoService.selectByBundleId(bundleId);
        return WebResponse.ok(appInfo);
    }

    @RequiresRoles(ApplicationConstant.USER_ROLE_ADMIN)
    @ApiOperation(value = "新增appInfo")
    @PostMapping("/add")
    public IResponse<AppInfoDto> addAppInfo(@RequestBody @Valid AppInfoDto appinfo){
        return WebResponse.ok(appInfoService.insert(appinfo));
    }

    @ApiOperation(value = "根据id删除appinfo")
    @RequiresRoles(ApplicationConstant.USER_ROLE_ADMIN)
    @DeleteMapping("/{appId}")
    public IResponse deleteAppInfoByAppId( @PathVariable("appId") @NotBlank(message = "appid不能为空") String appId){
        boolean b = appInfoService.deleteById(appId);
        return WebResponse.result(b);
    }

}
