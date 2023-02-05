package com.hackaton.project.exceptions.student;

public class StudentDoesNotExistsException extends RuntimeException {
    public StudentDoesNotExistsException() {
        super(
                String.format("Student doesn't exist")
        );
    }
}
