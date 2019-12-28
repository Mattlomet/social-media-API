package com.company.socialmedia.util.feign;

import com.company.socialmedia.model.Post;
import com.company.socialmedia.util.fallback.PostFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post-service",fallback = PostFallBack.class)
public interface PostFeign {

    //post
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Post createPost(@RequestBody Post post);
    //read
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Post getPostById(@PathVariable int id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<Post> getAllPosts();

    @GetMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    List<Post> getPostsByAccountId(@PathVariable int accountId);


    //update
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    void updatePost(@RequestBody Post post);

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePostById(@PathVariable int id);
}




