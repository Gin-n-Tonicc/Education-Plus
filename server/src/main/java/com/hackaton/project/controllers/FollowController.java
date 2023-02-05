package com.hackaton.project.controllers;

import com.hackaton.project.dtos.follow.FollowCreateDTO;
import com.hackaton.project.dtos.user.UserAuthDTO;
import com.hackaton.project.dtos.user.UserDTO;
import com.hackaton.project.entities.Business;
import com.hackaton.project.entities.Follow;
import com.hackaton.project.entities.Student;
import com.hackaton.project.exceptions.business.BusinessDoesNotExistsException;
import com.hackaton.project.exceptions.common.InsufficientPermissionsException;
import com.hackaton.project.exceptions.student.StudentDoesNotExistsException;
import com.hackaton.project.exceptions.user.UserIsAuthenticatedException;
import com.hackaton.project.repositories.FollowRepository;
import com.hackaton.project.services.BusinessService;
import com.hackaton.project.services.FollowService;
import com.hackaton.project.services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/followers")
public class FollowController {
    @Autowired
    FollowService followService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFollowingsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(followService.getByUserId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createFollowing(@RequestBody @Valid FollowCreateDTO followCreateDTO, HttpServletRequest request){
        UserAuthDTO userAuthDTO = (UserAuthDTO) request.getAttribute("user");
        boolean isAuthenticated = Objects.nonNull(request.getAttribute("isAuthenticated"));

        followService.createFollowing(userAuthDTO, followCreateDTO, isAuthenticated);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFollowing(@RequestBody @Valid FollowCreateDTO followCreateDTO, HttpServletRequest request) {
        UserAuthDTO userAuthDTO = (UserAuthDTO) request.getAttribute("user");
        boolean isAuthenticated = Objects.nonNull(request.getAttribute("isAuthenticated"));

        followService.deleteFollowing(userAuthDTO, followCreateDTO, isAuthenticated);
        return ResponseEntity.ok().build();
    }
}
