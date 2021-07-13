package com.sandipan.rest.restfulwebservices.service;

import com.sandipan.rest.restfulwebservices.entity.Post;
import com.sandipan.rest.restfulwebservices.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public List<Object[]> getPostById(Integer userId, Integer postId) {
        return postRepo.getPostById(userId, postId);
    }

    public Post addPost(Post post) {
        return postRepo.save(post);
    }

    public List<Post> getAllPost() {
        return postRepo.findAll();
    }

    public void deletePost(Integer userId, Integer postId) {
        postRepo.deleteUserPost(userId, postId);
    }
}
