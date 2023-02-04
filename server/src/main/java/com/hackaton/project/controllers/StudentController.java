package com.hackaton.project.controllers;

import com.hackaton.project.dtos.StudentDTO;
import com.hackaton.project.dtos.StudentLoginDTO;
import com.hackaton.project.dtos.UserAuthDTO;
import com.hackaton.project.dtos.UserDTO;
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
    public String submitUser(@Valid @RequestBody Student user) {
        Student submittedUser = studentService.submitStudent(user);
        UserDTO userAuthDTO = StudentDTO.mapToDTO(submittedUser);

        return jwtUtil.encode(userAuthDTO);
    }
    @PostMapping("/login")
    public String loginUser(HttpServletRequest request, @Valid @RequestBody StudentLoginDTO userLoginDTO) {
        Student loggedUser = studentService.loginStudent(userLoginDTO);
        UserDTO userAuthDTO = StudentDTO.mapToDTO(loggedUser);

        System.out.println(request.getAttribute("user") + " " + request.getAttribute("isAuthenticated"));

        return jwtUtil.encode(userAuthDTO);
    }

    @PostMapping("/test")
    public String test(HttpServletRequest request, @Valid @RequestBody StudentLoginDTO studentLoginDTO) {
        request.getAttribute("user");
        return "true";
    }
}
