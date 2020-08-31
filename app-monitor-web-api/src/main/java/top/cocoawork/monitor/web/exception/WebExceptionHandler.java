package top.cocoawork.monitor.web.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.rpc.RpcException;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.web.response.IResponse;
import top.cocoawork.monitor.web.response.WebResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ResponseBody
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = ShiroException.class)
    public IResponse exceptionHandler(ShiroException e) {
        Integer code = 403;
        String msg = "Access Deny！";
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public IResponse exceptionHandler(AuthenticationException e) {
        Integer code = 402;
        String msg = e.getMessage();
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = JWTDecodeException.class)
    public IResponse exceptionHandler(JWTDecodeException e) {
        Integer code = 401;
        String msg = "token无效";
        return new WebResponse(code, msg);
    }


    @ExceptionHandler(value = WebException.class)
    public IResponse exceptionHandler(WebException e) {
        Integer code = e.getCode();
        String msg = e.getMsg();
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = ServiceException.class)
    public IResponse exceptionHandler(ServiceException e) {
        Integer code = e.getCode();
        String msg = e.getMessage();
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = RpcException.class)
    public IResponse exceptionHandler(RpcException e) {
        Integer code = 601;
        String msg = "远程服务异常!";
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = Exception.class)
    public IResponse exceptionHandler(Exception e) {
        Integer code = 500;
        String msg = "未知错误";
        return new WebResponse(code, msg);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public IResponse exceptionHandler(MethodArgumentNotValidException e) {
        Integer code = 201;
        Set<String> errors = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toSet());
        String msg = StringUtils.join(errors, ",");
        return new WebResponse(code, msg);
    }


}
