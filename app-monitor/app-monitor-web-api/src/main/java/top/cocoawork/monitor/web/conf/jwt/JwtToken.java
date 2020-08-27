package top.cocoawork.monitor.web.conf.jwt;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    @Getter
    private String token;

    public JwtToken(String token) {
       this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }


}
