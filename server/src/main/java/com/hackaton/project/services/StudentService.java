package com.hackaton.project.services;

import com.hackaton.project.dtos.student.StudentLoginDTO;
import com.hackaton.project.dtos.user.UserAuthDTO;
import com.hackaton.project.entities.Student;
import com.hackaton.project.enums.Role;
import com.hackaton.project.exceptions.common.InsufficientPermissionsException;
import com.hackaton.project.exceptions.student.InvalidStudentDataException;
import com.hackaton.project.exceptions.student.StudentDoesNotExistsException;
import com.hackaton.project.exceptions.student.StudentExistsException;
import com.hackaton.project.repositories.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    public Student submitStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findOneByEmail(student.getEmail());
        student.setRole(Role.USER);

        if (optionalStudent.isPresent()) {
            throw new StudentExistsException("email");
        }

        return studentRepository.save(student);
    }

    public Student[] getAll() {
        return studentRepository.getAll();
    }

    public Student loginStudent(StudentLoginDTO studentLoginDTO) {
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

    public Student updateStudent(Long studentId, Student student, HttpServletRequest request) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            throw new StudentDoesNotExistsException();
        }
        if (!(studentId.equals(optionalStudent.get().getId()))){
            throw new InsufficientPermissionsException();
        }
            List<Student> studentList = (List<Student>) studentRepository.findAll();
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            if (s.getId().equals(studentId)) {
                studentList.set(i, student);
            }
        }
        student.setId(optionalStudent.get().getId());
        student.setRole(optionalStudent.get().getRole());
        return student;
    }
}
