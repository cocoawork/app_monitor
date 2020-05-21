package com.cocoawork.appstore.exception;



public enum ExceptionEnum {

    SERVICE_EXCEPTION(1000, "server exception!"),

    USER_LOGIN_FAILED(1001, "login failed!"),
    USER_LOGIN_ACCOUNT_ERROR(1002,"account error"),
    USER_LOGIN_PASSWORD_ERROR(1003,"password error"),
    USER_LOGIN_ACCOUNT_MULTI(1004,"account multi"),
    USER_LOGIN_ACCOUNT_NOT_EXIST(1005,"account not exist"),

    REQUEST_TOKEN_EXCEPTION(2000,"token error!"),
    REQUEST_TOKEN_EMPTY(2001, "token empty!"),

    ACCESS_FORBIDDEN(4003,"unauthority!"),
    NOT_LOGIN(4002,"not login!"),

    INVALID_PARAMETER(3000,"invalid parameter！"),

    ;

    private Integer code;
    private String message;
    private ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
