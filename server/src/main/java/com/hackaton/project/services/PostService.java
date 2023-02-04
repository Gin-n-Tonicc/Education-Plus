package com.hackaton.project.services;

import com.hackaton.project.dtos.UserAuthDTO;
import com.hackaton.project.entities.Post;
import com.hackaton.project.exceptions.InvalidUserPostException;
import com.hackaton.project.exceptions.UserIsAuthenticatedException;
import com.hackaton.project.repositories.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

import static com.hackaton.project.enums.Role.BUSINESS;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post[] getAll() {
        return postRepository.getAll();
    }

    public Post createPost(HttpServletRequest request, @Valid @RequestBody Post post) {
        UserAuthDTO optionalUser = (UserAuthDTO) request.getAttribute("userAuthDTO");
        boolean isAuthenticated = Objects.nonNull(request.getAttribute("isAuthenticated"));
        if(!isAuthenticated){
            throw new UserIsAuthenticatedException();
        }
        if (!optionalUser.getRole().equals(BUSINESS)) {
            throw new InvalidUserPostException();
        }
        post.setBusinessId(optionalUser.getId());
        postRepository.save(post);
        return post;
    }

}
