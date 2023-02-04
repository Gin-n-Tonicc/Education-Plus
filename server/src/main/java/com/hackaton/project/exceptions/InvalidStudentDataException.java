package com.hackaton.project.exceptions;

public class InvalidStudentDataException extends RuntimeException {
    public InvalidStudentDataException() {
        super(
                "Invalid email or password"
        );
    }
}
