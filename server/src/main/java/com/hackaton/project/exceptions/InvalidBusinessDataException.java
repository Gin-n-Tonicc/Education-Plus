package com.hackaton.project.exceptions;

public class InvalidBusinessDataException extends RuntimeException{
    public InvalidBusinessDataException() {
        super(
                "Invalid email or password"
        );
    }
}
