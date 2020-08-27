package top.cocoawork.monitor.service.api.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.cocoawork.monitor.common.exception.AbstractCustomException;

import java.io.Serializable;

public class ServiceException extends AbstractCustomException implements Serializable {

    public ServiceException() {
    }

    public ServiceException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public ServiceException(Integer code, String msg) {
        super(code, msg);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
