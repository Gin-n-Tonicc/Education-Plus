package com.hackaton.project.dtos.business;

import com.hackaton.project.dtos.user.UserDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.enums.Role;

public class BusinessDTO extends UserDTO {
    private String description;
    private String placeOfResidence;

    private boolean verified;
    public static BusinessDTO mapToDTO(Business business){
        return new BusinessDTO(business.getId(), business.getName(), business.getDescription() ,business.getEmail(), business.getPlaceOfResidence(), business.getRole(), business.isVerified());
    }


    public BusinessDTO(Long id, String name, String description, String email, String placeOfResidence, Role role, boolean verified) {
        super(id, name, email, role);
        this.description = description;
        this.placeOfResidence = placeOfResidence;
        this.verified = verified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
