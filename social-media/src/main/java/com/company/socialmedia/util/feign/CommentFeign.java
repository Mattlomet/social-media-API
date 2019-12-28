package com.company.socialmedia.util.feign;

import com.company.socialmedia.model.Comment;
import com.company.socialmedia.util.fallback.CommentFallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "comment-service", fallback = CommentFallBack.class)
public interface CommentFeign {

    //post
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Comment createComment(@RequestBody Comment comment);

    //read
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Comment getCommentById(@PathVariable int id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<Comment> getAllComments();

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    List<Comment> getCommentsByPostId(@PathVariable int postId);

    @GetMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    List<Comment> getCommentsByAccountId(@PathVariable int accountId);


    //update
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    void updateComment(@RequestBody Comment comment);

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCommentById(@PathVariable int id);


}
