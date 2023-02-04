package com.hackaton.project.exceptions.business;

public class InvalidBusinessDataException extends RuntimeException{
    public InvalidBusinessDataException() {
        super(
                "Invalid email or password"
        );
    }
}
