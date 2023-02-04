package com.hackaton.project.exceptions;

public class JWTInvalidException extends RuntimeException{
    public JWTInvalidException() {
        super("JWT token is invalid.");
    }
}
