package com.hackaton.project.services;

import com.hackaton.project.dtos.user.UserAuthDTO;
import com.hackaton.project.entities.Post;
import com.hackaton.project.enums.Role;
import com.hackaton.project.exceptions.common.EntityNotFoundException;
import com.hackaton.project.exceptions.common.InsufficientPermissionsException;
import com.hackaton.project.exceptions.user.UserIsAuthenticatedException;
import com.hackaton.project.repositories.PostRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.print.Pageable;
import java.util.*;

import static com.hackaton.project.enums.Role.BUSINESS;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post[] getAll() {
        return postRepository.getAll();
    }

    public Post getById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            throw new EntityNotFoundException(Post.class.getSimpleName());
        }

        return optionalPost.get();
    }

    public Post createPost(HttpServletRequest request,Post post) {
        UserAuthDTO optionalUser = (UserAuthDTO) request.getAttribute("user");
        boolean isAuthenticated = Objects.nonNull(request.getAttribute("isAuthenticated"));

        if(!isAuthenticated){
            throw new UserIsAuthenticatedException();
        }
        if (!optionalUser.getRole().equals(BUSINESS)) {
            throw new InsufficientPermissionsException();
        }

        post.setBusinessId(optionalUser.getId());
        return postRepository.save(post);
    }

    public void deletePostById(Long id, UserAuthDTO userAuthDTO, boolean isAuthenticated) {
        Post post = this.getById(id);

        if (!isAuthenticated) {
            throw new InsufficientPermissionsException();
        }

        if (userAuthDTO.getRole() == Role.STUDENT) {
            throw new InsufficientPermissionsException();
        }

        if (!userAuthDTO.getId().equals(post.getBusinessId())) {
            throw new InsufficientPermissionsException();
        }

        postRepository.delete(post);
    }

    public Post[] getPostsByLimit() {
        return postRepository.getRecentPosts();
    }

    public Post[] getRecentPostsById(Long businessId) {
        return postRepository.getRecentPostsById(businessId);
    }

}
