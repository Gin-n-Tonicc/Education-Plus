package com.hackaton.project.exceptions.user;

public class InvalidUserPostException extends RuntimeException{
    public InvalidUserPostException()  {
        super(
                "Can't create post, role isn't business"
        );
    }
}
