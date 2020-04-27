package com.cocoawork.appstore.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private Integer exceptionCode = -1;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.exceptionCode = exceptionEnum.getCode();
    }

}
