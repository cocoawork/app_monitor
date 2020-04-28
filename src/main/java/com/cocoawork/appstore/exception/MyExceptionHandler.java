package com.cocoawork.appstore.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestControllerAdvice
public class MyExceptionHandler {


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = AuthorizationException.class)
    public Response exceptionHandler(AuthorizationException e) {
        return new Response(ExceptionEnum.ACCESS_FORIDDEN);
    }


    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = AuthenticationException.class)
    public Response exceptionHandler(AuthenticationException e) {
        return new Response(ExceptionEnum.NOT_LOGIN);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = CustomException.class)
    public Response exceptionHandler(CustomException e) {
        return new Response(e.getExceptionCode() ,e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(Exception e) {
        return Response.fail(e.getMessage());
    }

}
