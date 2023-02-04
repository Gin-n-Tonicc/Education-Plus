package com.hackaton.project.exceptions.student;

public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String field) {
        super(
                String.format("Student with the same %s already exists", field)
        );
    }
}
