package com.hackaton.project.repositories;

import com.hackaton.project.entities.Business;
import com.hackaton.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query(value = "SELECT * FROM Post", nativeQuery = true)
    Post[] getAll();


    @Query(value = "SELECT p FROM Post p ORDER BY p.id DESC LIMIT 3")
    Post[] getRecentPosts();

    @Query(value = "SELECT p FROM Post p WHERE p.businessId = :businessId ORDER BY p.id DESC LIMIT 3")
    Post[] getRecentPostsById(@Param("businessId") Long businessId);
}
