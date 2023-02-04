package com.hackaton.project.repositories;

import com.hackaton.project.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findOneByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM User", nativeQuery = true)
    User[] getAll();
}
