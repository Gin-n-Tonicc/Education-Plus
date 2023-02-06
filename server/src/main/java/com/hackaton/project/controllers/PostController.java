package com.hackaton.project.controllers;

import com.hackaton.project.dtos.common.PostDTO;
import com.hackaton.project.dtos.user.UserAuthDTO;
import com.hackaton.project.entities.Post;
import com.hackaton.project.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @GetMapping
    public List<PostDTO> getAll() {
        return Arrays.stream(postService.getAll()).map(PostDTO::mapToDTO).toList();
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable("id") Long id) {
        return PostDTO.mapToDTO(postService.getById(id));
    }

    @GetMapping("/recent")
    public List<PostDTO> getRecentByLimit() {
        return Arrays.stream(postService.getPostsByLimit()).map(PostDTO::mapToDTO).toList();
    }

    @GetMapping("/recent/{businessId}")
    public List<PostDTO> getRecentById(@PathVariable("businessId") Long businessId) {
        return Arrays.stream(postService.getRecentPostsById(businessId)).map(PostDTO::mapToDTO).toList();
    }

    @PostMapping("/create")
    public PostDTO createPost(HttpServletRequest request, @Valid @RequestBody Post post) {
        Post createPost = postService.createPost(request, post);
        return PostDTO.mapToDTO(createPost);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id, HttpServletRequest request) {
        UserAuthDTO userAuthDTO = (UserAuthDTO) request.getAttribute("user");
        boolean isAuthenticated = Objects.nonNull(request.getAttribute("isAuthenticated"));

        postService.deletePostById(id, userAuthDTO, isAuthenticated);
        return ResponseEntity.ok().build();
    }
}
