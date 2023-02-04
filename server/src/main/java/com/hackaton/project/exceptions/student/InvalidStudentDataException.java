package com.hackaton.project.exceptions.student;

public class InvalidStudentDataException extends RuntimeException {
    public InvalidStudentDataException() {
        super(
                "Invalid email or password"
        );
    }
}
