package com.hackaton.project.dtos;

import com.hackaton.project.entities.Student;

public class StudentDTO {
    private Long id;
    private String fullName;
    private String email;
    public static StudentDTO mapToDTO(Student student){
        return new StudentDTO(student.getId(), student.getFullName(), student.getEmail());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentDTO(Long id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
}
