package com.company.socialmedia.util.fallback;

import com.company.socialmedia.model.Comment;
import com.company.socialmedia.util.feign.CommentFeign;
import com.netflix.hystrix.contrib.javanica.exception.FallbackInvocationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CommentFallBack implements CommentFeign {
    @Override
    public Comment createComment(Comment comment) {
        Comment returnComment = new Comment();
        returnComment.setAccountId(0);
        returnComment.setCommentId(0);
        returnComment.setPostId(0);
        returnComment.setComment("there has been an error creating comment");
        returnComment.setCreatedDate(null);
        return returnComment;
    }

    @Override
    public Comment getCommentById(int id) {
        Comment returnComment = new Comment();
        returnComment.setAccountId(0);
        returnComment.setCommentId(0);
        returnComment.setPostId(0);
        returnComment.setComment("there has been an error creating comment");
        returnComment.setCreatedDate(null);
        return returnComment;
    }

    @Override
    public List<Comment> getAllComments() {
        Comment returnComment = new Comment();
        returnComment.setAccountId(0);
        returnComment.setCommentId(0);
        returnComment.setPostId(0);
        returnComment.setComment("there has been an error creating comment");
        returnComment.setCreatedDate(null);

        List<Comment> commentList = new ArrayList<>(Collections.singleton(returnComment));
        return commentList;
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        Comment returnComment = new Comment();
        returnComment.setAccountId(0);
        returnComment.setCommentId(0);
        returnComment.setPostId(0);
        returnComment.setComment("there has been an error getting comment(s)");
        returnComment.setCreatedDate(null);

        List<Comment> commentList = new ArrayList<>(Collections.singleton(returnComment));
        return commentList;
    }

    @Override
    public List<Comment> getCommentsByAccountId(int accountId) {
        Comment returnComment = new Comment();
        returnComment.setAccountId(0);
        returnComment.setCommentId(0);
        returnComment.setPostId(0);
        returnComment.setComment("there has been an error getting comment(s)");
        returnComment.setCreatedDate(null);

        List<Comment> commentList = new ArrayList<>(Collections.singleton(returnComment));
        return commentList;
    }

    @Override
    public void updateComment(Comment comment) {
        System.out.println("error in - Comment update");
        throw new FallbackInvocationException();
    }

    @Override
    public void deleteCommentById(int id) {
        System.out.println("error in - Comment delete");
        throw new FallbackInvocationException();
    }
}
