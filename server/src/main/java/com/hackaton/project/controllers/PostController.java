package com.hackaton.project.controllers;

import com.hackaton.project.dtos.common.PostDTO;
import com.hackaton.project.entities.Post;
import com.hackaton.project.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @GetMapping
    public List<PostDTO> getAll() {
        return Arrays.stream(postService.getAll()).map(PostDTO::mapToDTO).toList();
    }
    @PostMapping("/create")
    public PostDTO createPost(HttpServletRequest request, @Valid @RequestBody Post post) {
        Post createPost = postService.createPost(request, post);
        return PostDTO.mapToDTO(createPost);
    }
}
