package com.hackaton.project.dtos.follow;

public class FollowCreateDTO {
    private Long studentId;
    private Long businessId;

    public FollowCreateDTO(Long studentId, Long businessId) {
        this.studentId = studentId;
        this.businessId = businessId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
