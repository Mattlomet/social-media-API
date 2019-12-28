package com.company.socialmedia.viewmodel;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class CommentViewModel {

    private int commentId;

    @NotNull(message = "you must supply a post id")
    private int postId;
    @NotNull(message = "you must supply an account id")
    private int accountId;
    @NotEmpty(message = "comment cannot be empty")
    private String comment;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    private AccountViewModel accountViewModel;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
        CommentViewModel that = (CommentViewModel) o;
        return commentId == that.commentId &&
                postId == that.postId &&
                accountId == that.accountId &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(accountViewModel, that.accountViewModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postId, accountId, comment, createdDate, accountViewModel);
    }

    @Override
    public String toString() {
        return "CommentViewModel{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", accountId=" + accountId +
                ", comment='" + comment + '\'' +
                ", createdDate=" + createdDate +
                ", accountViewModel=" + accountViewModel +
                '}';
    }
}
