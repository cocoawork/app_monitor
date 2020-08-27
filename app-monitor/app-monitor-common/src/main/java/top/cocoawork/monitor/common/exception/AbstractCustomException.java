package top.cocoawork.monitor.common.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
abstract public class AbstractCustomException extends RuntimeException implements Serializable {

    private Integer code;

    private String msg;

    private Throwable cause;

    public AbstractCustomException() {
    }

    public AbstractCustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AbstractCustomException(Throwable throwable) {
        this.code = -1;
        this.msg = "System Error!";
        this.cause = throwable;
    }


    public AbstractCustomException(Integer code, String msg, Throwable cause) {
        this.code = code;
        this.msg = msg;
        this.cause = cause;
    }



}
