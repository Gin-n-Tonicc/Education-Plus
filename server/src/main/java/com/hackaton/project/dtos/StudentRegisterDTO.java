package com.hackaton.project.dtos;

import com.hackaton.project.entities.Student;

public class StudentRegisterDTO {
    private String token;
    private UserAuthDTO student;
    public StudentRegisterDTO(String token, UserAuthDTO student) {
        this.token = token;
        this.student = student;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserAuthDTO getStudent() {
        return student;
    }

    public void setStudent(UserAuthDTO student) {
        this.student = student;
    }
}
