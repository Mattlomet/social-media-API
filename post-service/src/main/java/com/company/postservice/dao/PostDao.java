package com.company.postservice.dao;

import com.company.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
    List<Post> getPostsByAccountId(int accountId);
}
