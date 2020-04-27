package com.cocoawork.appstore.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.exception.ExceptionEnum;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    public static final String AUTH_KEY = "Author";

    private static final Long EXPIRE_TIME = 60 * 60 * 24 * 1000L;

    private static final String TOKEN_SECRET = "zxcvbnmasdfghjklqwertyuiop0123456789";

    private static final Algorithm ALGORITHM = Algorithm.HMAC256(TOKEN_SECRET);


    public static String genreToken(String userId) {
        Date expired = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Map header = new HashMap<String,Object>();
        header.put("type", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .sign(ALGORITHM);
    }


    private static DecodedJWT verify(String token) {
        if (StringUtils.isEmpty(token)){
            throw new CustomException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION);
        }
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            return verifier.verify(token);
        }catch (JWTVerificationException e) {
            throw new CustomException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION);
        }

    }

    public static String decodeUserId(String token) {
        DecodedJWT decodedJWT = verify(token);
        String userId = decodedJWT.getClaim("userId").asString();
        return userId;
    }


}
