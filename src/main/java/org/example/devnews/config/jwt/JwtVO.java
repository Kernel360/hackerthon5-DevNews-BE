package org.example.devnews.config.jwt;

public interface JwtVO {
    public static final String SECRET = "meta";
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}
