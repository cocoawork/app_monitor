package top.cocoawork.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebResponseObject<T> extends WebResponse {

    private T data;

    public WebResponseObject(T data) {
        super();
        this.data = data;
    }

    public WebResponseObject(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public static WebResponseObject ok(Object data) {
        return new WebResponseObject<>(data);
    }
}
