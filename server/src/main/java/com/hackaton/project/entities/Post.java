package com.hackaton.project.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    @NotBlank
    private String interests;
    @NotBlank
    private String ageGroup;
    @NotBlank
    private String requirements;
    private Date deadlineToParticipate;
    @NotBlank
    private String linkToApplicationForm;
    @NotBlank
    private String place;
    private Long businessId;
    private Date startDate;
    private Date endDate;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Date getDeadlineToParticipate() {
        return deadlineToParticipate;
    }

    public void setDeadlineToParticipate(Date deadlineToParticipate) {
        this.deadlineToParticipate = deadlineToParticipate;
    }

    public String getLinkToApplicationForm() {
        return linkToApplicationForm;
    }

    public void setLinkToApplicationForm(String linkToApplicationForm) {
        this.linkToApplicationForm = linkToApplicationForm;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", interests='" + interests + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", requirements='" + requirements + '\'' +
                ", deadlineToParticipate=" + deadlineToParticipate +
                ", linkToApplicationForm='" + linkToApplicationForm + '\'' +
                ", place='" + place + '\'' +
                ", businessId=" + businessId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
