package com.hackaton.project.dtos.student;

import com.hackaton.project.dtos.user.UserAuthDTO;

public class StudentResponseDTO {
    private String token;
    private UserAuthDTO student;
    public StudentResponseDTO(String token, UserAuthDTO student) {
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
