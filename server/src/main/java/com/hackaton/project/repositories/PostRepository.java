package com.hackaton.project.repositories;

import com.hackaton.project.entities.Business;
import com.hackaton.project.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post", nativeQuery = true)
    Post[] getAll();
}
