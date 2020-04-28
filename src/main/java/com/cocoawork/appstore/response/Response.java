package com.cocoawork.appstore.response;

import com.cocoawork.appstore.constant.Constant;
import com.cocoawork.appstore.exception.ExceptionEnum;
import lombok.Data;

@Data
public class Response {
    private Integer code;
    private String msg;


    public Response() {
        this.code = 0;
        this.msg = Constant.SUCCESS;
    }

    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Response ok() {
        return new Response(0, Constant.SUCCESS);
    }

    public static Response fail() {
        return new Response(-1, Constant.FAILED);
    }

    public static Response fail(String message) {
        return new Response(-1, message);
    }

    public Response(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMessage();
    }
}
