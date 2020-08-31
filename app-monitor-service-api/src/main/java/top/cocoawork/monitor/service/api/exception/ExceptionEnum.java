package top.cocoawork.monitor.service.api.exception;

public enum ExceptionEnum {
    /**
    * 用户注册失败
    **/
    USER_REGIST_EXCEPTION(100, "用户注册失败"),

    /**
     * 用户登录异常
     **/
    USER_LOGIN_EXCEPTION(101, "用户登录失败"),

    /**
     *指定用户不存在的异常
     */
    USER_NOT_EXIST_EXCEPTION(102, "用户不存在"),

    /**
     *参数异常
     */
    PARAMETER_EXCEPTION(201,"参数异常"),
    ;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
