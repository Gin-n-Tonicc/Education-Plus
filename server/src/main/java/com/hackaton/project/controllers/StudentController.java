package com.hackaton.project.controllers;

import com.hackaton.project.dtos.StudentDTO;
import com.hackaton.project.dtos.StudentLoginDTO;
import com.hackaton.project.entities.Student;
import com.hackaton.project.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return Arrays.stream(studentService.getAll()).map(StudentDTO::mapToDTO).toList();
    }

    @PostMapping("/register")
    public StudentDTO submitUser(@Valid @RequestBody Student user) {
        Student submittedUser = studentService.submitStudent(user);
         return StudentDTO.mapToDTO(submittedUser);
    }
    @PostMapping("/login")
    public StudentDTO loginUser(@Valid @RequestBody StudentLoginDTO userLoginDTO) {
        Student loggedUser = studentService.loginStudent(userLoginDTO);
        return StudentDTO.mapToDTO(loggedUser);
    }
}
