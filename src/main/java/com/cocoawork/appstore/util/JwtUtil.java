package com.cocoawork.appstore.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cocoawork.appstore.entity.User;
import com.cocoawork.appstore.exception.CustomException;
import com.cocoawork.appstore.exception.ExceptionEnum;
import org.springframework.util.StringUtils;

import java.rmi.server.UID;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String USERNAME_KEY = "UNAME";

    private static final String USERID_KEY = "UID";

    private static final Long EXPIRE_TIME = 10 * 60 * 1000L;

//    private static final String TOKEN_SECRET = "zxcvbnmasdfghjklqwertyuiop0123456789";
//
//    private static final Algorithm ALGORITHM = Algorithm.HMAC256(TOKEN_SECRET);



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


    public static Boolean verify(String token, String secret) {
        if (StringUtils.isEmpty(token)){
            throw new CustomException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION);
        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build();
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
            throw new CustomException(ExceptionEnum.REQUEST_TOKEN_EXCEPTION);
        }

    }

    public static String decodeUserId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String userId = decodedJWT.getClaim(USERID_KEY).asString();
            return userId;
        }catch (JWTDecodeException e) {
            throw new CustomException(e.getMessage());
        }

    }

    public static String decodeUserName(String token) {
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            String uname = decodedJWT.getClaim(USERNAME_KEY).asString();
            return uname;
         }catch (JWTDecodeException e) {
            throw new CustomException(e.getMessage());
        }
    }


}
