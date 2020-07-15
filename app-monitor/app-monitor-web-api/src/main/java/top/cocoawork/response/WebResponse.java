package top.cocoawork.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WebResponse implements IResponse {

    private Integer code = 0;
    private String msg = "ok";

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
        if (okOrFail){
            return ok();
        }else {
            return fail();
        }
    }

}
