package com.hackaton.project.exceptions.common;

public class InsufficientPermissionsException extends RuntimeException{
    public InsufficientPermissionsException()  {
        super(
                "Not enough permissions to perform that action"
        );
    }
}
