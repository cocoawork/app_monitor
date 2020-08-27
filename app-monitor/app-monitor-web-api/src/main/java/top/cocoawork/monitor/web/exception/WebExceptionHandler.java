package top.cocoawork.monitor.web.exception;

import org.apache.dubbo.rpc.RpcException;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.service.api.exception.CustomServiceException;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;

@ResponseBody
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = ShiroException.class)
    public IResponse exceptionHandler(ShiroException e) {
        Integer code = 105;
        String msg = "未认证";
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = CustomWebException.class)
    public IResponse exceptionHandler(CustomWebException e) {
        Integer code = e.getCode();
        String msg = e.getMsg();
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = CustomServiceException.class)
    public IResponse exceptionHandler(CustomServiceException e) {
        Integer code = e.getCode();
        String msg = e.getMessage();
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = RpcException.class)
    public IResponse exceptionHandler(RpcException e) {
        Integer code = 501;
        String msg = "远程服务异常";
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = Exception.class)
    public IResponse exceptionHandler(Exception e) {
        Integer code = 500;
        String msg = "未知错误";
        return new WebResponse(code, msg);
    }

}
