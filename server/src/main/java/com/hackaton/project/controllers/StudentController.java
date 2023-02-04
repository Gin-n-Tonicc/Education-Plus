package com.hackaton.project.controllers;

import com.hackaton.project.dtos.*;
import com.hackaton.project.entities.Student;
import com.hackaton.project.repositories.StudentRepository;
import com.hackaton.project.services.StudentService;
import com.hackaton.project.utils.JwtUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    JwtUtilImpl jwtUtil;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return Arrays.stream(studentService.getAll()).map(StudentDTO::mapToDTO).toList();
    }

    @PostMapping("/register")
    public StudentResponseDTO submitUser(@Valid @RequestBody Student user) {
        Student submittedUser = studentService.submitStudent(user);
        UserDTO userAuthDTO = StudentDTO.mapToDTO(submittedUser);
        String token = jwtUtil.encode(userAuthDTO);

        return new StudentResponseDTO(token, userAuthDTO);
    }
    @PostMapping("/login")
    public StudentResponseDTO loginUser(HttpServletRequest request, @Valid @RequestBody StudentLoginDTO userLoginDTO) {
        Student loggedUser = studentService.loginStudent(userLoginDTO);
        UserDTO userAuthDTO = StudentDTO.mapToDTO(loggedUser);
        String token = jwtUtil.encode(userAuthDTO);

        System.out.println(request.getAttribute("user") + " " + request.getAttribute("isAuthenticated"));
        return new StudentResponseDTO(token, userAuthDTO);
    }

    @PostMapping("/me")
    public UserDTO details(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getAttribute("user");
        return user;
    }
}
