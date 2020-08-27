package top.cocoawork.monitor.web.exception;

import lombok.Getter;
import lombok.Setter;
import top.cocoawork.monitor.service.api.exception.ExceptionEnum;
import top.cocoawork.monitor.web.response.IResponse;

public class CustomWebException extends RuntimeException implements IResponse {

    @Setter
    @Getter
    private Integer code;

    private String msg;

    public String getMsg() {
        return this.getMessage();
    }

    public CustomWebException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomWebException(String message) {
        super(message);
        this.code = 500;
    }

    public CustomWebException(ExceptionEnum exceptionConstant){
        super(exceptionConstant.getMsg());
        this.code = exceptionConstant.getCode();
    }

    public CustomWebException(WebExceptionEnum exceptionConstant){
        super(exceptionConstant.getMsg());
        this.code = exceptionConstant.getCode();
    }

}
