package com.hackaton.project.dtos;

import com.hackaton.project.entities.Student;
import com.hackaton.project.enums.Role;

public class StudentDTO extends UserDTO {
    public static StudentDTO mapToDTO(Student student){
        return new StudentDTO(student.getId(), student.getFullName(), student.getEmail(), student.getRole());
    }

    public StudentDTO(Long id, String fullName, String email, Role role) {
        super(id, fullName, email, role);
    }
}
