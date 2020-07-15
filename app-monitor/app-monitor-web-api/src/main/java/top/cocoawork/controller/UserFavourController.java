package top.cocoawork.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.constant.Constant;
import top.cocoawork.model.UserApp;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;
import top.cocoawork.response.WebResponseObject;
import top.cocoawork.service.UserAppService;

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
        String userId = (String) request.getAttribute(Constant.REQUEST_UID_KEY);
        List<UserApp> userApps = userAppService.selectUserAppsByUserId(userId);
        return WebResponseObject.ok(userApps);
    }


    @PostMapping("/add")
    public IResponse addFavour(@RequestParam("appId") String appId) {
        String userId = (String) request.getAttribute(Constant.REQUEST_UID_KEY);
        UserApp userApp = new UserApp();
        userApp.setAppId(appId);
        userApp.setUserId(userId);
        boolean b = userAppService.inserUserApp(userApp);
        return WebResponse.result(b);
    }

    @DeleteMapping("/appId")
    public IResponse deleteFavor(@PathVariable("appId") String appId){
        String userId = (String) request.getAttribute(Constant.REQUEST_UID_KEY);
        UserApp userApp = new UserApp();
        userApp.setAppId(appId);
        userApp.setUserId(userId);
        boolean b = userAppService.deleteUserApp(userApp);
        return WebResponse.result(b);
    }




}
