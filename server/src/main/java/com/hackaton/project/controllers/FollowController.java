package com.hackaton.project.controllers;

import com.hackaton.project.dtos.follow.FollowCreateDTO;
import com.hackaton.project.services.BusinessService;
import com.hackaton.project.services.FollowService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/followers")
public class FollowController {
    @Autowired
    FollowService followService;
//    @PostMapping("/create")
//    public Object createFollowing(@RequestBody @Valid FollowCreateDTO followCreateDTO, HttpServletRequest request){
//
//    }
}
