package com.hackaton.project.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String field) {
        super(
                String.format("User with the same %s already exists", field)
        );
    }
}
