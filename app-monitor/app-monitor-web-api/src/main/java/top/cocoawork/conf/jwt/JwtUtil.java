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
    private static final String USERNAME_KEY = "UNAME";

    private static final String USERID_KEY = "UID";

    private static final Long EXPIRE_TIME = 10 * 60 * 1000L;

    public static String genreToken(String userId, String userName, String secret) {
        Date expired = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map header = new HashMap<String,Object>();
        header.put("type", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim(USERID_KEY, userId)
                .withClaim(USERNAME_KEY, userName)
                .withIssuedAt(new Date())
                .withExpiresAt(expired)
                .sign(Algorithm.HMAC256(secret));
    }


    public static Boolean verify(String token, String secret) throws CustomWebException {
        if (StringUtils.isEmpty(token)){
            throw new CustomWebException(WebExceptionEnum.UNAVALIABLE_TOKEN_EXCEPTION);
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build();
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
            throw new CustomWebException(WebExceptionEnum.UNAVALIABLE_TOKEN_EXCEPTION);
        }

    }

    public static String decodeUserId(String token) throws CustomWebException {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String userId = decodedJWT.getClaim(USERID_KEY).asString();
            return userId;
        }catch (JWTDecodeException e) {
            throw new CustomWebException(WebExceptionEnum.UNAVALIABLE_TOKEN_EXCEPTION);
        }

    }

    public static String decodeUserName(String token) throws CustomWebException {
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            String uname = decodedJWT.getClaim(USERNAME_KEY).asString();
            return uname;
        }catch (JWTDecodeException e) {
            throw new CustomWebException(WebExceptionEnum.UNAVALIABLE_TOKEN_EXCEPTION);
        }
    }
}
