package com.hackaton.project.exceptions;

public class UserIsAuthenticatedException extends RuntimeException{
    public UserIsAuthenticatedException() {
        super(
                "User doesn't exist"
        );
    }
}
