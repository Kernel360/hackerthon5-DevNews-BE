package org.example.devnews.config.jwt;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtVO {
    @Value("$({jwt.secret}")
    public static final String SECRET = "meta";

    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
