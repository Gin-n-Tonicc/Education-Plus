package com.hackaton.project.services;

import com.hackaton.project.dtos.follow.FollowCreateDTO;
import com.hackaton.project.dtos.user.UserAuthDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.entities.Follow;
import com.hackaton.project.entities.Student;
import com.hackaton.project.exceptions.business.BusinessDoesNotExistsException;
import com.hackaton.project.exceptions.common.InsufficientPermissionsException;
import com.hackaton.project.exceptions.follow.AlreadyFollowingException;
import com.hackaton.project.exceptions.student.StudentDoesNotExistsException;
import com.hackaton.project.exceptions.user.UserIsAuthenticatedException;
import com.hackaton.project.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {
    @Autowired
    StudentService studentService;

    @Autowired
    BusinessService businessService;
    @Autowired
    FollowRepository followRepository;
    public Follow[] getAll() {
        return followRepository.getAll();
    }

    public Follow[] getByUserId(Long id) {
        Optional<Student> optionalStudent = studentService.getById(id);
        if (optionalStudent.isEmpty()) {
            throw new StudentDoesNotExistsException();
        }

        return followRepository.getByUserId(id);
    }

    public void createFollowing(UserAuthDTO userAuthDTO, FollowCreateDTO followCreateDTO, boolean isAuthenticated){
        if (!isAuthenticated) {
            throw new UserIsAuthenticatedException();
        }

        if (!userAuthDTO.getId().equals(followCreateDTO.getStudentId())) {
            throw new InsufficientPermissionsException();
        }

        Optional<Student> optionalStudent = studentService.getById(followCreateDTO.getStudentId());
        if (optionalStudent.isEmpty()) {
            throw new StudentDoesNotExistsException();
        }

        Student student = optionalStudent.get();
        Optional<Business> business = businessService.getById(followCreateDTO.getBusinessId());
        if (business.isEmpty()) {
            throw new BusinessDoesNotExistsException();
        }

        boolean isFollowing = followRepository.getByUserId(student.getId()).length >= 1;

        if (isFollowing) {
            throw new AlreadyFollowingException();
        }

        Follow following = new Follow();
        following.setFollowedStudent(student);
        following.setGotFollowedBusiness(business.get());

        followRepository.save(following);
    }

    public void deleteFollowing(UserAuthDTO userAuthDTO, FollowCreateDTO followCreateDTO, boolean isAuthenticated) {
        if (!isAuthenticated) {
            throw new UserIsAuthenticatedException();
        }

        if (!userAuthDTO.getId().equals(followCreateDTO.getStudentId())) {
            throw new InsufficientPermissionsException();
        }

        Optional<Student> optionalStudent = studentService.getById(followCreateDTO.getStudentId());
        if (optionalStudent.isEmpty()) {
            throw new StudentDoesNotExistsException();
        }

        Optional<Business> optionalBusiness = businessService.getById(followCreateDTO.getBusinessId());
        if (optionalBusiness.isEmpty()) {
            throw new BusinessDoesNotExistsException();
        }

        Student student = optionalStudent.get();
        Business business = optionalBusiness.get();

        Follow[] follows = followRepository.getByUserIdAndBusinessId(student.getId(), business.getId());
        followRepository.deleteAll(List.of(follows));
    }
}
