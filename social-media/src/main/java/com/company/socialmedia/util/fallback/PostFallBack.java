package com.company.socialmedia.util.fallback;

import com.company.socialmedia.model.Post;
import com.company.socialmedia.util.feign.PostFeign;
import com.netflix.hystrix.contrib.javanica.exception.FallbackInvocationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PostFallBack implements PostFeign {

    @Override
    public Post createPost(Post post) {
        Post returnPost = new Post();
        returnPost.setAccountId(0);
        returnPost.setPost("there has been an error creating post");
        returnPost.setCreatedDate(null);
        returnPost.setPostId(0);
        return returnPost;
    }

    @Override
    public Post getPostById(int id) {
        Post returnPost = new Post();
        returnPost.setAccountId(0);
        returnPost.setPost("there has been an error getting post(s)");
        returnPost.setCreatedDate(null);
        returnPost.setPostId(0);
        return returnPost;
    }

    @Override
    public List<Post> getAllPosts() {
        Post returnPost = new Post();
        returnPost.setAccountId(0);
        returnPost.setPost("there has been an error getting post(s)");
        returnPost.setCreatedDate(null);
        returnPost.setPostId(0);

        List<Post> postList = new ArrayList<>(Collections.singleton(returnPost));
        return postList;
    }

    @Override
    public List<Post> getPostsByAccountId(int accountId) {
        Post returnPost = new Post();
        returnPost.setAccountId(0);
        returnPost.setPost("there has been an error getting post(s)");
        returnPost.setCreatedDate(null);
        returnPost.setPostId(0);

        List<Post> postList = new ArrayList<>(Collections.singleton(returnPost));
        return postList;
    }

    @Override
    public void updatePost(Post post) {
        System.out.println("error in - Post update");
        throw new FallbackInvocationException();
    }

    @Override
    public void deletePostById(int id) {
        System.out.println("error in - Post delete");
        throw new FallbackInvocationException();
    }

}
