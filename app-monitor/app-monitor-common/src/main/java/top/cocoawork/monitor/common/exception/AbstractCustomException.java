package top.cocoawork.monitor.common.exception;

import java.io.Serializable;

abstract public class AbstractCustomException extends RuntimeException implements Serializable {

    private Integer code;

    private String msg;

    private Throwable cause;

    public AbstractCustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AbstractCustomException(Throwable throwable) {
        this.code = -1;
        this.msg = "error";
        this.cause = throwable;
    }


    public AbstractCustomException(Integer code, String msg, Throwable cause) {
        this.code = code;
        this.msg = msg;
        this.cause = cause;
    }
}
