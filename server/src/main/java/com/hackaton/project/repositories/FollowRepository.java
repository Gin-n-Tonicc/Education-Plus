package com.hackaton.project.repositories;

import com.hackaton.project.entities.Follow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends CrudRepository<Follow, Long> {
    @Query(value = "SELECT * FROM Follow", nativeQuery = true)
    Follow[] getAll();

    @Query(value = "SELECT f FROM Follow f WHERE f.followedStudent.id = :id")
    Follow[] getByUserId(@Param("id") Long id);

    @Query(value = "SELECT f FROM Follow f WHERE f.followedStudent.id = :userId AND f.gotFollowedBusiness.id = :businessId")
    Follow[] getByUserIdAndBusinessId(@Param("userId") Long userId, @Param("businessId") Long businessId);
}
