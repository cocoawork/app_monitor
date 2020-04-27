package com.cocoawork.appstore.config.Jwt;

import com.cocoawork.appstore.annotation.IgnoreToken;
import com.cocoawork.appstore.exception.ExceptionEnum;
import com.cocoawork.appstore.exception.UserException;
import com.cocoawork.appstore.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            IgnoreToken annotation = handlerMethod.getMethodAnnotation(IgnoreToken.class);
            if (annotation != null) {
                return true;
            }
        }

        String token = request.getHeader(JwtUtil.AUTH_KEY);
        String userId = JwtUtil.decodeUserId(token);
        request.setAttribute("UID", userId);
        return true;
    }
}
