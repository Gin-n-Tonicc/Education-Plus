package com.hackaton.project.repositories;

import com.hackaton.project.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query(value = "SELECT u FROM Student u WHERE u.email = :email")
    Optional<Student> findOneByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM Student", nativeQuery = true)
    Student[] getAll();
}
