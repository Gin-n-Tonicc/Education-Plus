package com.hackaton.project.exceptions.jwts;

public class JWTInvalidException extends RuntimeException{
    public JWTInvalidException() {
        super("JWT token is invalid.");
    }
}
