package top.cocoawork.exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.exception.CustomServiceException;
import top.cocoawork.exception.CustomWebException;
import top.cocoawork.exception.ExceptionEnum;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;

@ResponseBody
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    public IResponse exceptionHandler(AuthenticationException e) {
        Integer code = 10005;
        String msg = "未认证";
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public IResponse exceptionHandler(UnauthenticatedException e) {
        Integer code = 10004;
        String msg = "未授权";
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

    @ExceptionHandler(value = Exception.class)
    public IResponse exceptionHandler(Exception e) {
        Integer code = 50000;
        String msg = "未知异常";
        return new WebResponse(code, msg);
    }

}
