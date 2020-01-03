package com.company.socialmedia.controller;


import com.company.socialmedia.exception.AccountException;
import com.company.socialmedia.exception.CommentException;
import com.company.socialmedia.exception.PostException;
import com.company.socialmedia.service.ServiceLayer;
import com.company.socialmedia.viewmodel.AccountViewModel;
import com.company.socialmedia.viewmodel.CommentViewModel;
import com.company.socialmedia.viewmodel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SocialController {

    @Autowired
    ServiceLayer serviceLayer;


    //create account
    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountViewModel createAccount(@RequestBody @Valid AccountViewModel accountViewModel) throws AccountException {
        return serviceLayer.createAccount(accountViewModel);
    }

    //get account by id
    @GetMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountViewModel getAccountById(@PathVariable int accountId) throws AccountException {
        return serviceLayer.getAccountById(accountId);
    }

    //update account
    @PutMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@RequestBody @Valid AccountViewModel accountViewModel){
        serviceLayer.updateAccount(accountViewModel);
    }

    //delete account
    @DeleteMapping("/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable int accountId){
        serviceLayer.deleteAccount(accountId);
    }

    //create post
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostViewModel createPost(@RequestBody @Valid PostViewModel postViewModel) throws PostException {
        return serviceLayer.createPost(postViewModel);
    }

    //get post by id
    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel getPostById(@PathVariable int postId) {
        return serviceLayer.getPostById(postId);
    }

    //get all posts
    @GetMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getAllPosts() {
        return serviceLayer.getAllPosts();
    }

    //update post
    @PutMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody @Valid PostViewModel postViewModel){
        serviceLayer.updatePost(postViewModel);
    }

    //delete post
    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable int postId){
        serviceLayer.deletePost(postId);
    }



    //create comment
    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentViewModel createComment(@RequestBody @Valid CommentViewModel commentViewModel) throws CommentException {
        return serviceLayer.createComment(commentViewModel);
    }

    //get comment by id
    @GetMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentViewModel getCommentById(@PathVariable int commentId) throws CommentException {
        return serviceLayer.getCommentById(commentId);
    }

    //get comment by account id
    @GetMapping("/comment/account/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentViewModel> getCommentsByAccountId(@PathVariable int accountId){
        return serviceLayer.getCommentByAccountId(accountId);
    }

    //update comment
    @PutMapping("/comment")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@RequestBody @Valid CommentViewModel commentViewModel){
        serviceLayer.updateComment(commentViewModel);
    }

    //delete comment
    @DeleteMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable int commentId){
        serviceLayer.deleteComment(commentId);
    }

}
