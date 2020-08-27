package top.cocoawork.monitor.web.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.common.constant.Constant;
import top.cocoawork.monitor.service.api.UserAppService;
import top.cocoawork.monitor.service.api.model.UserAppDto;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiresUser
@RequestMapping("/favour")
@RestController
public class UserFavourController {

    @Autowired
    private HttpServletRequest request;

    @Reference
    private UserAppService userAppService;


    @GetMapping
    public IResponse getFavourList(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(Constant.REQUEST_HEADER_UID_KEY);
        List<UserAppDto> userApps = userAppService.selectUserAppsByUserId(userId);
        return WebResponse.ok(userApps);
    }


    @PostMapping("/add")
    public IResponse addFavour(@RequestParam("appId") String appId) {
        String userId = (String) request.getAttribute(Constant.REQUEST_HEADER_UID_KEY);
        UserAppDto userApp = new UserAppDto();
        userApp.setAppId(appId);
        userApp.setUserId(userId);
        return WebResponse.result(userAppService.inserUserApp(userApp));
    }

    @DeleteMapping("/appId")
    public IResponse deleteFavor(@PathVariable("appId") String appId){
        String userId = (String) request.getAttribute(Constant.REQUEST_HEADER_UID_KEY);
        UserAppDto userApp = new UserAppDto();
        userApp.setAppId(appId);
        userApp.setUserId(userId);
        boolean b = userAppService.deleteUserApp(userApp);
        return WebResponse.result(b);
    }




}
