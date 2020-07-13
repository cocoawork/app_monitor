package top.cocoawork.conf;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.cocoawork.exception.CustomServiceException;
import top.cocoawork.exception.CustomWebException;
import top.cocoawork.response.IResponse;
import top.cocoawork.response.WebResponse;

@RestControllerAdvice
public class WebExceptionHandler {

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
        Integer code = 500;
        String msg = "未知系统错误";
        return new WebResponse(code, msg);
    }

}
