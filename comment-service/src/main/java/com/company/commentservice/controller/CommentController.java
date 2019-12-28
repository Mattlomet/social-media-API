package com.company.commentservice.controller;

import com.company.commentservice.dao.CommentDao;
import com.company.commentservice.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentDao commentDao;

    //post
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment){
        return commentDao.save(comment);
    }

    //read
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getCommentById(@PathVariable int id){
        return commentDao.getOne(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComments(){
        return commentDao.findAll();
    }


    //update
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@RequestBody Comment comment){
        commentDao.save(comment);
    }

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommentById(@PathVariable int id){
        commentDao.deleteById(id);
    }
}
