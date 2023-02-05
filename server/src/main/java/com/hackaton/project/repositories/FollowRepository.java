package com.hackaton.project.repositories;

import com.hackaton.project.entities.Follow;
import com.hackaton.project.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FollowRepository extends CrudRepository<Follow, Long> {
    @Query(value = "SELECT * FROM Follow", nativeQuery = true)
    Follow[] getAll();
}
