package com.cocoawork.appstore.exception;

import com.cocoawork.appstore.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletResponse;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public Response exceptionHandler(CustomException e, ServletResponse response) {
        return Response.fail();
    }
}
