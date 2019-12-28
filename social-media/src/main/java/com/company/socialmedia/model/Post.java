package com.company.socialmedia.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Objects;

public class Post {

    private int postId;

    private int accountId;
    private String post;
    private LocalDate createdDate;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post1 = (Post) o;
        return postId == post1.postId &&
                accountId == post1.accountId &&
                Objects.equals(post, post1.post) &&
                Objects.equals(createdDate, post1.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, accountId, post, createdDate);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", accountId=" + accountId +
                ", post='" + post + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
