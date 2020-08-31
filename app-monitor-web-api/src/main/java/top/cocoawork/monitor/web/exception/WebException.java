package top.cocoawork.monitor.web.exception;

import lombok.Getter;
import lombok.Setter;
import top.cocoawork.monitor.common.exception.AbstractCustomException;
import top.cocoawork.monitor.service.api.exception.ExceptionEnum;
import top.cocoawork.monitor.web.response.IResponse;

public class WebException extends AbstractCustomException implements IResponse {


    public WebException(Integer code, String msg) {
        super(code, msg);
    }

    public WebException(ExceptionEnum exceptionConstant){
        super(exceptionConstant.getCode(), exceptionConstant.getMsg());
    }

    public WebException(WebExceptionEnum exceptionConstant){
        super(exceptionConstant.getCode(), exceptionConstant.getMsg());
    }

}
