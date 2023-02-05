package com.hackaton.project.services;

import com.hackaton.project.entities.Follow;
import com.hackaton.project.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    FollowRepository followRepository;
    public Follow[] getAll() {
        return followRepository.getAll();
    }
//    public Follow createFollowing(Long businessId, Long studentId){
//
//    }
}
