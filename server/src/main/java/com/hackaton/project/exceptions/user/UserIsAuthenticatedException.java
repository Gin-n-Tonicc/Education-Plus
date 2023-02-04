package com.hackaton.project.exceptions.user;

public class UserIsAuthenticatedException extends RuntimeException{
    public UserIsAuthenticatedException() {
        super(
                "User doesn't exist"
        );
    }
}
