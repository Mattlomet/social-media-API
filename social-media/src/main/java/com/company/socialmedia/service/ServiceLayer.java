package com.company.socialmedia.service;

import com.company.socialmedia.exception.AccountException;
import com.company.socialmedia.exception.CommentException;
import com.company.socialmedia.exception.PostException;
import com.company.socialmedia.model.Account;
import com.company.socialmedia.model.Comment;
import com.company.socialmedia.model.Post;
import com.company.socialmedia.util.feign.AccountFeign;
import com.company.socialmedia.util.feign.CommentFeign;
import com.company.socialmedia.util.feign.PostFeign;
import com.company.socialmedia.viewmodel.AccountViewModel;
import com.company.socialmedia.viewmodel.CommentViewModel;
import com.company.socialmedia.viewmodel.PostViewModel;
import com.netflix.hystrix.contrib.javanica.exception.FallbackInvocationException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import org.apache.tomcat.jni.Error;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLayer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "account-exchange";
    private static final String ROUTING_KEY_DELETE = "account.delete";
    private static final String ROUTING_KEY_UPDATE = "account.update";


    @Autowired
    AccountFeign accountFeign;

    @Autowired
    CommentFeign commentFeign;

    @Autowired
    PostFeign postFeign;


    // methods for controllers

    // account model methods

    public AccountViewModel createAccount(AccountViewModel accountViewModel) throws AccountException {
        Account account = new Account();
        account.setAccountName(accountViewModel.getAccountName());
        account.setCreatedDate(accountViewModel.getCreatedDate());

        account = accountFeign.createAccount(account);

        if (account.getAccountName() == "there has been a problem with getting account info") {
            throw new AccountException("there has been an error with account service");
        }

        return buildAccountViewModel(account);
    }

    public AccountViewModel getAccountById(int accountId) throws AccountException {
        Account account = accountFeign.getAccountById(accountId);

        if (account.getAccountName() == "there has been a problem with getting account info") {
            throw new AccountException("there has been an error with account service");
        }

        return buildAccountViewModel(account);
    }

    public void updateAccount(AccountViewModel accountViewModel){
        Account account = new Account();
        account.setAccountId(accountViewModel.getAccountId());
        account.setAccountName(accountViewModel.getAccountName());
        account.setCreatedDate(accountViewModel.getCreatedDate());


        System.out.println(".....updating account");
        accountFeign.updateAccount(account);
    }

    public void deleteAccount(int accountId){
        System.out.println(".....deleting account");
        rabbitTemplate.convertAndSend(EXCHANGE,ROUTING_KEY_DELETE,accountId);
    }




    // post model methods

    public PostViewModel createPost(PostViewModel postViewModel) throws PostException {
        Post post = new Post();
        post.setPost(postViewModel.getPost());
        post.setCreatedDate(postViewModel.getCreatedDate());
        post.setAccountId(postViewModel.getAccountId());

        post = postFeign.createPost(post);

        if (post.getPost() == "there has been an error getting post(s)") {
            throw new PostException("there has been an error with post service");
        }

        return buildPostViewModel(post);
    }

    public PostViewModel getPostById(int postId){
        return buildPostViewModel(postFeign.getPostById(postId));
    }

    public List<PostViewModel> getAllPosts(){
        List<PostViewModel> postViewModelList = new ArrayList<>();
        postFeign.getAllPosts().forEach(post -> {
            if (post.getPost() == "there has been an error getting post(s)") {
                try {
                    throw new PostException("there has been an error with post service");
                } catch (PostException e) {
                    e.printStackTrace();
                }
            }
            postViewModelList.add(buildPostViewModel(post));
        });
        return  postViewModelList;
    }

    public void updatePost(PostViewModel postViewModel){
        Post post = new Post();
        post.setPostId(postViewModel.getPostId());
        post.setPost(postViewModel.getPost());
        post.setCreatedDate(postViewModel.getCreatedDate());
        post.setAccountId(postViewModel.getAccountId());

        System.out.println(".....updating post");
        postFeign.updatePost(post);
    }

    public void deletePost(int postId){
        System.out.println(".....deleting post");
        postFeign.deletePostById(postId);
    }




    // comment model methods

    public CommentViewModel createComment(CommentViewModel commentViewModel) throws CommentException {
        Comment comment = new Comment();
        comment.setPostId(commentViewModel.getPostId());
        comment.setAccountId(commentViewModel.getAccountId());
        comment.setComment(commentViewModel.getComment());
        comment.setCreatedDate(commentViewModel.getCreatedDate());

        comment = commentFeign.createComment(comment);

        if (comment.getComment() == "there has been an error creating comment") {
            throw new CommentException("there has been an error with comment service");
        }

        return buildCommentViewModel(comment);
    }

    public CommentViewModel getCommentById(int id) throws CommentException {

        Comment comment = commentFeign.getCommentById(id);

        if (comment.getComment() == "there has been an error getting comment(s)") {
            throw new CommentException("there has been an error with comment service");
        }

        return buildCommentViewModel(comment);
    }

    public List<CommentViewModel> getCommentByAccountId(int accountId){
        List<CommentViewModel> commentViewModelList = new ArrayList<>();
        commentFeign.getCommentsByAccountId(accountId).forEach(comment -> {
            if (comment.getComment() == "there has been an error getting comment(s)") {
                try {
                    throw new CommentException("there has been an error with comment service");
                } catch (CommentException e) {
                    e.printStackTrace();
                }
            }
            commentViewModelList.add(buildCommentViewModel(comment));
        });
        return commentViewModelList;
    }

    public void updateComment(CommentViewModel commentViewModel){
        Comment comment = new Comment();
        comment.setCommentId(commentViewModel.getCommentId());
        comment.setPostId(commentViewModel.getPostId());
        comment.setAccountId(commentViewModel.getAccountId());
        comment.setComment(commentViewModel.getComment());
        comment.setCreatedDate(commentViewModel.getCreatedDate());

        System.out.println(".....updating comment");
        commentFeign.updateComment(comment);
    }

    public void deleteComment(int commentId){
        System.out.println(".....deleting comment");
        commentFeign.deleteCommentById(commentId);
    }




    // build view model methods

    public AccountViewModel buildAccountViewModel(Account account){
        AccountViewModel avm = new AccountViewModel();
        avm.setAccountId(account.getAccountId());
        avm.setAccountName(account.getAccountName());
        avm.setCreatedDate(account.getCreatedDate());
        return avm;
    }

    public CommentViewModel buildCommentViewModel(Comment comment){
        CommentViewModel cvm = new CommentViewModel();
        cvm.setAccountId(comment.getAccountId());
        cvm.setCommentId(comment.getCommentId());
        cvm.setComment(comment.getComment());
        cvm.setPostId(comment.getPostId());
        cvm.setCreatedDate(comment.getCreatedDate());

        cvm.setAccountViewModel(buildAccountViewModel(accountFeign.getAccountById(comment.getAccountId())));

        return cvm;
    }

    public PostViewModel buildPostViewModel(Post post){
        PostViewModel pvm = new PostViewModel();
        pvm.setPostId(post.getPostId());
        pvm.setAccountId(post.getAccountId());
        pvm.setPost(post.getPost());
        pvm.setCreatedDate(post.getCreatedDate());

        pvm.setAccountViewModel(buildAccountViewModel(accountFeign.getAccountById(post.getAccountId())));

        pvm.setCommentList(getCommentByPostId(post.getPostId()));

        return pvm;
    }



    // helper methods

    public List<CommentViewModel> getCommentByPostId(int postId){
        List<CommentViewModel> commentViewModelList = new ArrayList<>();

        commentFeign.getCommentsByPostId(postId).forEach(comment -> {
            if (comment.getComment() == "there has been an error getting comment(s)") {
                try {
                    throw new CommentException("there has been an error with comment service");
                } catch (CommentException e) {
                    e.printStackTrace();
                }
            }
            commentViewModelList.add(buildCommentViewModel(comment));
        });

        return commentViewModelList;
    }


}
