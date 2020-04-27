package com.cocoawork.appstore.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cocoawork.appstore.response.Response;
import com.cocoawork.appstore.response.ResponseData;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(Exception e, ServletResponse response) {
        try {
            PrintWriter writer = response.getWriter();
            Response r = null;
            if (e instanceof CustomException) {
                CustomException customException = (CustomException) e;
                r = new Response(customException.getExceptionCode(), e.getMessage());
            }else {
                r = Response.fail(e.getMessage());
            }
            String jsonString = JSON.toJSONString(r);
            writer.write(jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public void exceptionHandler(AuthorizationException e, ServletResponse response) {
        try {
            PrintWriter writer = response.getWriter();
            Response r = new Response(403,"access foridden!");
            writer.write(JSON.toJSONString(r));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
