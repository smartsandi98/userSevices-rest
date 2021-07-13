package com.sandipan.rest.restfulwebservices.repo;

import com.sandipan.rest.restfulwebservices.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    @Query(value = "select post_id,post_description from Post p where p.user_id=:userId and p.post_id=:postId", nativeQuery = true)
    public List<Object[]> getPostById(@Param("userId") Integer userId, @Param("postId") Integer postId);


    //to manipulate DB value using custom query we have to specify that we are modifying DB
    // and as well as we have enable transaction for this by telling transactional
    @Modifying
    @Transactional
    @Query(value = "delete from Post p where p.user_id=:userId and p.post_id=:postId", nativeQuery = true)
    public void deleteUserPost(@Param("userId") Integer userId, @Param("postId") Integer postId);

}
