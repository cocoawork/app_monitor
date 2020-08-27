package top.cocoawork.monitor.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "WebResponse", description = "返回json格式包装类")
public class WebResponse<T> implements IResponse<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code = 0;

    @ApiModelProperty(value = "请求信息")
    private String msg = "ok";

    @ApiModelProperty(value = "请求数据内容")
    private T data;

    public WebResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static WebResponse ok() {
        return new WebResponse();
    }

    public static WebResponse fail(){
        WebResponse response = new WebResponse();
        response.setCode(-1);
        response.setMsg("fail");
        return response;
    }

    public static WebResponse result(Boolean okOrFail){
        return okOrFail ? ok() : fail();
    }

    public static WebResponse ok(Object data) {
        WebResponse response = new WebResponse<>();
        response.data = data;
        return response;
    }
}
