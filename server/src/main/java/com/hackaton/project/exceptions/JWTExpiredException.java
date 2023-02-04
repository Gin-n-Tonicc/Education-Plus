package com.hackaton.project.exceptions;

public class JWTExpiredException extends RuntimeException {
    public JWTExpiredException() {
        super("JWT token has expired.");
    }
}
