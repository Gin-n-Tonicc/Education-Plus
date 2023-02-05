package com.hackaton.project.dtos.jwt;

import com.hackaton.project.dtos.user.UserAuthDTO;

public class JwtParseDTO {
    private String token;
    private UserAuthDTO user;

    public JwtParseDTO(String token, UserAuthDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserAuthDTO getUser() {
        return user;
    }

    public void setUser(UserAuthDTO user) {
        this.user = user;
    }
}
