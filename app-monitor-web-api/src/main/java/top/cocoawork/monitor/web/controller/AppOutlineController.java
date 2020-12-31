package top.cocoawork.monitor.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.common.constant.ApplicationConstant;
import top.cocoawork.monitor.service.api.dto.AppOutlineDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;
import top.cocoawork.monitor.service.api.AppOutlineService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Api(tags = "app简介信息", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/appOutline")
@Validated
@Slf4j
public class AppOutlineController {

    @Reference
    private AppOutlineService appOutlineService;

    @ApiOperation(value = "根据id获取app简介")
    @GetMapping("/{appId}")
    public WebResponse<AppOutlineDto> getAppOutline(@PathVariable("appId") @NotBlank(message = "appid不能为空") String appId){
        AppOutlineDto appOutline = appOutlineService.selectById(appId);
        return WebResponse.ok(appOutline);

    }

    @ApiOperation(value = "获取app简介列表")
    @GetMapping
    public WebResponse<List<AppOutlineDto>> getAppOutline(@RequestParam(value = "pageIndex", defaultValue = "0") @Min(0) Integer pageIndex,
                                   @RequestParam(value = "pageSize", defaultValue = "10") @Min(1) @Max(100) Integer pageSize,
                                   @RequestParam(value = "country",required = false) String country){
        List<AppOutlineDto> appOutlines = appOutlineService.selectPage(country, pageIndex, pageSize);
        log.info("收到请求,结果：{}", appOutlines.size());
        return WebResponse.ok(appOutlines);
    }


    @ApiOperation(value = "根据id删除app简介")
    @RequiresRoles(ApplicationConstant.USER_ROLE_ADMIN)
    @DeleteMapping("/{appId}")
    public IResponse deleteAppOutline(@PathVariable("appId")@NotBlank(message = "appid不能为空") String appId){
        boolean b = appOutlineService.deleteById(appId);
        return WebResponse.result(b);
    }

    @ApiOperation(value = "插入app简介信息")
    @RequiresRoles(ApplicationConstant.USER_ROLE_ADMIN)
    @PostMapping("/add")
    public IResponse<AppOutlineDto> addAppOutline(@RequestBody @Valid AppOutlineDto appOutline){
        appOutlineService.insert(appOutline);
        return WebResponse.ok(appOutline);
    }



}
