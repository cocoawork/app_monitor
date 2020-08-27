package top.cocoawork.monitor.service.api.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CustomServiceException extends RuntimeException implements Serializable {

    private Integer code;

    public CustomServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomServiceException(ExceptionEnum exceptionConstant){
        super(exceptionConstant.getMsg());
        this.code = exceptionConstant.getCode();
    }


}
