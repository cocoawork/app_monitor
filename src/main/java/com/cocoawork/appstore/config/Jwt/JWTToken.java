package com.cocoawork.appstore.config.Jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    /*
    * 返回用户名
    * */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /*
    * 返回密码
    * */
    @Override
    public Object getCredentials() {
        return token;
    }
}
