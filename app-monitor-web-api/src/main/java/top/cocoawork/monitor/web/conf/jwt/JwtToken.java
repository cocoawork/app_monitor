package top.cocoawork.monitor.web.conf.jwt;

import lombok.Data;
import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    @Getter
    private String token;
    @Getter
    private JwtTokenMetaData metaData;


    private JwtToken() {}

    public JwtToken(String token) {
       this.token = token;
       this.metaData = new JwtTokenMetaData(token);
    }

    /**
     * 返回用户名
     * */
    @Override
    public Object getPrincipal() {
        return token;
    }

    /**
     * 返回用户id
     * */
    @Override
    public Object getCredentials() {
        return token;
    }

    public Long getUserId() {
        return metaData.getUserId();
    }

    public String getUsername() {
        return metaData.getUsername();
    }

    public String getRoles() {
        return metaData.getRoles();
    }

    @Getter
    static class JwtTokenMetaData {

        private Long userId;
        private String username;
        private String roles;

        public JwtTokenMetaData(String token) {
            this.userId = JwtUtil.decode4UserId(token);
            this.username = JwtUtil.decode4UserName(token);
            this.roles = JwtUtil.decode4UserRole(token);
        }
    }

}
