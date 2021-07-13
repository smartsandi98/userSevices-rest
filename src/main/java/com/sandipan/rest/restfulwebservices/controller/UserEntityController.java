package com.sandipan.rest.restfulwebservices.controller;

import com.sandipan.rest.restfulwebservices.entity.Post;
import com.sandipan.rest.restfulwebservices.exception.UserException;
import com.sandipan.rest.restfulwebservices.entity.UserEntity;
import com.sandipan.rest.restfulwebservices.service.PostService;
import com.sandipan.rest.restfulwebservices.service.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/jpa")
public class UserEntityController {

    private final UserEntityService service;
    private final PostService postService;

    @Autowired
    public UserEntityController(UserEntityService service, PostService postService) {
        this.service = service;
        this.postService = postService;
    }

    @GetMapping("/users")
    public List<UserEntity> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<UserEntity> retrieveUser(@PathVariable int id) {
        Optional<UserEntity> user = service.findOne(id);
        if (user.isEmpty()) {
            throw new UserException("user not found " + id);
        }
        //Creating HATEOAS entity model to return data and action items
        EntityModel<UserEntity> model = EntityModel.of(user.get());

        //for building link using HATEOAS using WebMvcLinkBuilder
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.
                linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                        .retrieveAllUsers());
        model.add(linkToUsers.withRel("All-Users"));
        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserEntity user) {
        UserEntity userEntity = service.addUser(user);
        log.info("Created user {} ", userEntity);
        URI uriOfResourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uriOfResourceLocation).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveUsersAllPosts(@PathVariable Integer id) {
        Optional<UserEntity> users = service.findOne(id);
        if (users.isEmpty()) {
            throw new UserException("User not found for id " + id);
        }
        return users.get().getPosts();
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public Post retrieveUsersPostById(@PathVariable Integer userId, @PathVariable Integer postId) {
        List<Object[]> value = postService.getPostById(userId, postId);
        Integer id = (Integer) Array.get(value.get(0), 0);
        String description = (String) Array.get(value.get(0), 1);
        return new Post(id, description);

    }

    @GetMapping("/posts/all-posts")
    public List<Post> getAllPosts() {
        return postService.getAllPost();
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable Integer userId, @RequestBody Post post) {

        Optional<UserEntity> users = service.findOne(userId);
        if (users.isEmpty()) {
            throw new UserException("User not found for id " + userId);
        }

        UserEntity userEntity = service.addUser(users.get());
        post.setUserEntity(userEntity);
        postService.addPost(post);
        URI uriOfResourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uriOfResourceLocation).build();
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public void deletePosts(@PathVariable Integer userId, @PathVariable Integer postId) {
        Optional<UserEntity> user = service.findOne(userId);
        if (user.isEmpty()) {
            throw new UserException("User Not Found For ID " + userId);
        }
        postService.deletePost(userId, postId);
    }
}
