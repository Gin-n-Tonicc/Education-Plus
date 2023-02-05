package com.hackaton.project.exceptions.business;

public class BusinessDoesNotExistsException extends RuntimeException {
    public BusinessDoesNotExistsException() {
        super(
                String.format("Business doesn't exist")
        );
    }
}
