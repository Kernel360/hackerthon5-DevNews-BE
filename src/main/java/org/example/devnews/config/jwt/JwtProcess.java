package org.example.devnews.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.devnews.config.security.LoginUser;
import org.example.devnews.domain.user.User;
import org.example.devnews.domain.user.UserEnum;


import java.util.Date;

public class JwtProcess {
    public static String create(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject("devnews")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
                .withClaim("id", loginUser.getUser().getId())
                .withClaim("role", loginUser.getUser().getRole() + "")
                .sign(Algorithm.HMAC512(JwtVO.SECRET));
        return JwtVO.TOKEN_PREFIX + jwtToken;
    }

    public static LoginUser verify(String token) {
        DecodedJWT decodeJWT = JWT.require(Algorithm.HMAC512(JwtVO.SECRET)).build().verify(token);
        Long id = decodeJWT.getClaim("id").asLong();
        String role = decodeJWT.getClaim("role").asString();
        User user = User.builder().id(id).role(UserEnum.valueOf(role)).build();
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }
}