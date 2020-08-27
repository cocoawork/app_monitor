package top.cocoawork.monitor.util.exception;

import top.cocoawork.monitor.common.exception.AbstractCustomException;

public class UtilException extends AbstractCustomException {

    public UtilException(Integer code, String msg) {
        super(code, msg);
    }

    public UtilException(Throwable throwable) {
        super(throwable);
    }

    public UtilException(Integer code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
}
