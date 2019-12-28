package com.company.socialmedia.viewmodel;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PostViewModel {


    private int postId;

    @NotNull(message = "please supply a account id")
    private int accountId;
    @NotEmpty(message = "post message cannot be empty")
    private String post;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    private List<CommentViewModel> commentList;
    private AccountViewModel accountViewModel;

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

    public List<CommentViewModel> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentViewModel> commentList) {
        this.commentList = commentList;
    }

    public AccountViewModel getAccountViewModel() {
        return accountViewModel;
    }

    public void setAccountViewModel(AccountViewModel accountViewModel) {
        this.accountViewModel = accountViewModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostViewModel that = (PostViewModel) o;
        return postId == that.postId &&
                accountId == that.accountId &&
                Objects.equals(post, that.post) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(commentList, that.commentList) &&
                Objects.equals(accountViewModel, that.accountViewModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, accountId, post, createdDate, commentList, accountViewModel);
    }

    @Override
    public String toString() {
        return "PostViewModel{" +
                "postId=" + postId +
                ", accountId=" + accountId +
                ", post='" + post + '\'' +
                ", createdDate=" + createdDate +
                ", commentList=" + commentList +
                ", accountViewModel=" + accountViewModel +
                '}';
    }
}
