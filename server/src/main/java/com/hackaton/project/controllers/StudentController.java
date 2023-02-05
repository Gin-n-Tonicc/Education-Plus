package com.hackaton.project.controllers;

import com.hackaton.project.dtos.student.StudentDTO;
import com.hackaton.project.dtos.student.StudentLoginDTO;
import com.hackaton.project.dtos.student.StudentProfileDTO;
import com.hackaton.project.dtos.student.StudentResponseDTO;
import com.hackaton.project.dtos.user.UserDTO;
import com.hackaton.project.entities.Student;
import com.hackaton.project.exceptions.common.EntityNotFoundException;
import com.hackaton.project.services.StudentService;
import com.hackaton.project.utils.JwtUtilImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    JwtUtilImpl jwtUtil;
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return Arrays.stream(studentService.getAll()).map(StudentDTO::mapToDTO).toList();
    }

    @GetMapping("/{id}")
    public StudentProfileDTO getById(@PathVariable("id") Long id) throws EntityNotFoundException {
        EntityNotFoundException exception = new EntityNotFoundException(Student.class.getSimpleName());

        if (Objects.isNull(id)) {
            throw exception;
        }

        Optional<Student> student = studentService.getById(id);

        if (student.isEmpty()) {
            throw exception;
        }

        return new StudentProfileDTO(student.get());
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
    @PutMapping("/update/{id}")
    public StudentProfileDTO updateStudent(@PathVariable("id") Long studentId, @RequestBody @Valid Student student, HttpServletRequest request) {
        Student updateStudent = studentService.updateStudent(studentId, student, request);
        return new StudentProfileDTO(updateStudent);
    }
}
