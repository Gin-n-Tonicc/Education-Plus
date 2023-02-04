package com.hackaton.project.exceptions;

public class InvalidUserPostException extends RuntimeException{
    public InvalidUserPostException()  {
        super(
                "Can't create post, role isn't business"
        );
    }
}
