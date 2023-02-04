package com.hackaton.project.exceptions.student;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student was not found.");
    }
}
