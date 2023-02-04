package com.hackaton.project.dtos;

import com.hackaton.project.entities.Business;
import com.hackaton.project.enums.Role;

public class BusinessDTO extends UserDTO {
    private String description;
    private String placeOfResidence;
    public static BusinessDTO mapToDTO(Business business){
        return new BusinessDTO(business.getId(), business.getName(), business.getDescription() ,business.getEmail(), business.getPlaceOfResidence(), business.getRole());
    }

    public BusinessDTO(Long id, String name, String description, String email, String placeOfResidence, Role role) {
        super(id, name, email, role);
        this.description = description;
        this.placeOfResidence = placeOfResidence;
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
}
