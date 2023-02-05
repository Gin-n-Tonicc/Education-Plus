package com.hackaton.project.services;

import com.hackaton.project.dtos.student.StudentLoginDTO;
import com.hackaton.project.entities.Student;
import com.hackaton.project.enums.Role;
import com.hackaton.project.exceptions.student.InvalidStudentDataException;
import com.hackaton.project.exceptions.student.StudentExistsException;
import com.hackaton.project.repositories.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }
    public Student submitStudent(@Valid @RequestBody Student student) {
        Optional<Student> optionalStudent = studentRepository.findOneByEmail(student.getEmail());
        student.setRole(Role.STUDENT);

        if (optionalStudent.isPresent()) {
            throw new StudentExistsException("email");
        }

        return studentRepository.save(student);
    }
    public Student[] getAll() {
        return studentRepository.getAll();
    }
    public Student loginStudent(@Valid @RequestBody StudentLoginDTO studentLoginDTO) {
        Optional<Student> optionalStudent = studentRepository.findOneByEmail(studentLoginDTO.getEmail());

        if (optionalStudent.isEmpty()) {
            throw new InvalidStudentDataException();
        }

        Student student = optionalStudent.get();
        if (!student.getPassword().equals(studentLoginDTO.getPassword())) {
            throw new InvalidStudentDataException();
        }
        return student;
    }
}
