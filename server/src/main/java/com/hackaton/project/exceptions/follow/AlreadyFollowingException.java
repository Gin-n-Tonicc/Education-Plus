package com.hackaton.project.exceptions.follow;

public class AlreadyFollowingException extends RuntimeException{
    public AlreadyFollowingException() {
        super("User already follows this business.");
    }
}
