package com.hackaton.project.exceptions.jwts;

public class JWTExpiredException extends RuntimeException {
    public JWTExpiredException() {
        super("JWT token has expired.");
    }
}
