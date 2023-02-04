package com.hackaton.project.dtos;

import com.hackaton.project.entities.Business;

public class BusinessDTO {
    private Long id;
    private String name;
    private String description;
    private String placeOfResidence;
    private String email;
    public static BusinessDTO mapToDTO(Business business){
        return new BusinessDTO(business.getId(), business.getName(), business.getDescription() ,business.getEmail(), business.getPlaceOfResidence());
    }

    public BusinessDTO(Long id, String name, String description, String placeOfResidence, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.placeOfResidence = placeOfResidence;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
