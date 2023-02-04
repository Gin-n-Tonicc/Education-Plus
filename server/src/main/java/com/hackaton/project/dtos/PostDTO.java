package com.hackaton.project.dtos;

import com.hackaton.project.entities.Business;
import com.hackaton.project.entities.Post;

import java.sql.Date;

public class PostDTO {
    Long id;
    String description;
    String category;
    String interests;
    String ageGroup;
    String requirements;
    Date deadlineToParticipate;
    String linkToApplicationForm;
    Long businessId;
    String place;
    Date startDate;
    Date endDate;
    public static PostDTO mapToDTO(Post post){
        return new PostDTO(post.getId(), post.getDescription(), post.getCategory(), post.getInterests(), post.getAgeGroup(), post.getRequirements(), post.getDeadlineToParticipate(), post.getLinkToApplicationForm(), post.getPlace(), post.getStartDate(), post.getEndDate(), post.getBusinessId());
    }

    public PostDTO(Long id, String description, String category, String interests, String ageGroup, String requirements, Date deadlineToParticipate, String linkToApplicationForm, String place, Date startDate, Date endDate, Long businessId) {
        this.id = id;
        this.description = description;
        this.category = category;
        this.interests = interests;
        this.ageGroup = ageGroup;
        this.requirements = requirements;
        this.deadlineToParticipate = deadlineToParticipate;
        this.linkToApplicationForm = linkToApplicationForm;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.businessId = businessId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
