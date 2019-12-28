package com.company.postservice.controller;

import com.company.postservice.dao.PostDao;
import com.company.postservice.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostDao postDao;

    //post
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post){
        return postDao.save(post);
    }

    //read
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post getPostById(@PathVariable int id){
        return postDao.getOne(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPosts(){
        return postDao.findAll();
    }


    //update
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody Post post){
        postDao.save(post);
    }

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostById(@PathVariable int id){
        postDao.deleteById(id);
    }
}
