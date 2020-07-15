package top.cocoawork.conf.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;
import top.cocoawork.exception.CustomWebException;
import top.cocoawork.exception.ExceptionEnum;
import top.cocoawork.exception.WebExceptionEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String USERNAME_KEY = "USERNAME";

    private static final String USERID_KEY = "USER_ID";

    private static final Long EXPIRE_TIME = 10 * 60 * 1000L;

    private static final String SECRET_KEY = "*03zfxcgEv.bn%Aga1SD_+";


    public static String genreToken(String userId, String userName, String password) {
        Date expired = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map<String, Object> header = new HashMap<String,Object>();
        header.put("type", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim(USERID_KEY, userId)
                .withClaim(USERNAME_KEY, userName)
                .withIssuedAt(new Date())
                .withExpiresAt(expired)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }


    public static Boolean verify(String token) throws JWTVerificationException {
        if (StringUtils.isEmpty(token)) {
            throw new CustomWebException(WebExceptionEnum.UNAVALIABLE_TOKEN_EXCEPTION);
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build();
        verifier.verify(token);
        return true;


    }

    public static String decode4UserId(String token) throws JWTDecodeException {

        DecodedJWT decodedjwt = JWT.decode(token);
        return decodedjwt.getClaim(USERID_KEY).asString();

    }

    public static String decode4UserName(String token) throws JWTDecodeException {

        DecodedJWT decodedjwt = JWT.decode(token);
        return decodedjwt.getClaim(USERNAME_KEY).asString();

    }

}
