package com.hackaton.project.repositories;

import com.hackaton.project.entities.Business;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BusinessRepository extends CrudRepository<Business, Long> {
    @Query(value = "SELECT b FROM Business b WHERE b.email = :email")
    Optional<Business> findOneByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM Business", nativeQuery = true)
    Business[] getAll();
}
