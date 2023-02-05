package com.hackaton.project.dtos.business;

import com.hackaton.project.dtos.user.UserAuthDTO;

public class BusinessResponseDTO {
    private String token;
    private UserAuthDTO business;
    public BusinessResponseDTO(String token, UserAuthDTO business) {
        this.token = token;
        this.business = business;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserAuthDTO getBusiness() {
        return business;
    }

    public void setBusiness(UserAuthDTO business) {
        this.business = business;
    }
}
