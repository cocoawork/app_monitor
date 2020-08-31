package top.cocoawork.monitor.web.conf.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;
import top.cocoawork.monitor.web.exception.WebException;
import top.cocoawork.monitor.web.exception.WebExceptionEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String USERNAME_KEY = "USERNAME";

    private static final String USERID_KEY = "USER_ID";

    private static final String USER_ROLE_KEY = "USER_ROLE";

    private static final Long EXPIRE_TIME = 10 * 60 * 1000L * 100000L;

    private static final String SECRET_KEY = "*03zfxcgEv.bn%Aga1SD_+";


    public static String genreToken(Long userId, String userName, String role) {
        Date expired = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> header = new HashMap<String,Object>();
        header.put("type", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim(USERID_KEY, userId)
                .withClaim(USERNAME_KEY, userName)
                .withClaim(USER_ROLE_KEY, role)
                .withIssuedAt(new Date())
                .withExpiresAt(expired)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }


    public static Boolean verify(String token) throws JWTVerificationException {
        if (StringUtils.isEmpty(token)) {
            throw new WebException(WebExceptionEnum.UNAVALIABLE_TOKEN_EXCEPTION);
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build();
        verifier.verify(token);
        return true;


    }

    public static Long decode4UserId(String token) throws JWTDecodeException {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token).getClaim(USERID_KEY).asLong();
    }

    public static String decode4UserName(String token) throws JWTDecodeException {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token).getClaim(USERNAME_KEY).asString();
    }

    public static String decode4UserRole(String token) throws JWTDecodeException {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token).getClaim(USER_ROLE_KEY).asString();
    }

}
