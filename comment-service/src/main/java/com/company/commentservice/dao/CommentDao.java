package com.company.commentservice.dao;


import com.company.commentservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentByPostId(int postId);
    List<Comment> getCommentByAccountId(int accountId);
}
