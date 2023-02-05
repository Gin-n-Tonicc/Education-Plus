package com.hackaton.project.dtos.student;

import com.hackaton.project.entities.Student;

public class StudentProfileDTO extends StudentDTO {
    String school;
    String town;
    public StudentProfileDTO(Student student) {
        super(student.getId(), student.getFullName(), student.getEmail(), student.getRole());
        this.school = student.getSchool();
        this.town = student.getTown();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
