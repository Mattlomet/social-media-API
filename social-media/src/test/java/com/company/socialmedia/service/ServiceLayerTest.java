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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceLayerTest {

    @Autowired
    ServiceLayer serviceLayer;

    @MockBean
    AccountFeign accountFeign;

    @MockBean
    PostFeign postFeign;

    @MockBean
    CommentFeign commentFeign;


    @Before
    public void setUp() throws Exception {
        setUpAccountFeignMock();
        setUpCommentFeignMock();
        setUpPostFeignMock();
    }

    @Test
    public void createAccount() throws AccountException {
        AccountViewModel avm = new AccountViewModel();
        avm.setAccountName("test account");
        avm.setCreatedDate(LocalDate.of(10,10,10));

        AccountViewModel avm1 = new AccountViewModel();
        avm1.setAccountId(1);
        avm1.setAccountName("test account");
        avm1.setCreatedDate(LocalDate.of(10,10,10));


        assertEquals(avm1 ,serviceLayer.createAccount(avm));
    }

    @Test
    public void getAccountById() throws AccountException {

        AccountViewModel avm1 = new AccountViewModel();
        avm1.setAccountId(1);
        avm1.setAccountName("test account");
        avm1.setCreatedDate(LocalDate.of(10,10,10));


        assertEquals(avm1 ,serviceLayer.getAccountById(avm1.getAccountId()));
    }

    @Test
    public void createPost() throws PostException {
        PostViewModel post = new PostViewModel();
        post.setPost("test post");
        post.setAccountId(1);
        post.setCreatedDate(LocalDate.of(11,11,11));

        PostViewModel post1 = new PostViewModel();
        post1.setPostId(1);
        post1.setPost("test post");
        post1.setAccountId(1);
        post1.setCreatedDate(LocalDate.of(11,11,11));


        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        post1.setAccountViewModel(account1);


        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);

        List<CommentViewModel> commentList = new ArrayList<>(Collections.singleton(comment1));

        post1.setCommentList(commentList);


        assertEquals(post1,serviceLayer.createPost(post));
    }

    @Test
    public void getPostById() {
        PostViewModel post1 = new PostViewModel();
        post1.setPostId(1);
        post1.setPost("test post");
        post1.setAccountId(1);
        post1.setCreatedDate(LocalDate.of(11,11,11));


        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        post1.setAccountViewModel(account1);


        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);

        List<CommentViewModel> commentList = new ArrayList<>(Collections.singleton(comment1));

        post1.setCommentList(commentList);


        assertEquals(post1,serviceLayer.getPostById(post1.getPostId()));
    }

    @Test
    public void getAllPosts() {
        PostViewModel post1 = new PostViewModel();
        post1.setPostId(1);
        post1.setPost("test post");
        post1.setAccountId(1);
        post1.setCreatedDate(LocalDate.of(11,11,11));


        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        post1.setAccountViewModel(account1);


        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);

        List<CommentViewModel> commentList = new ArrayList<>(Collections.singleton(comment1));

        post1.setCommentList(commentList);


        List<PostViewModel> postViewModelList = new ArrayList<>(Collections.singleton(post1));

        assertEquals(postViewModelList,serviceLayer.getAllPosts());
    }

    @Test
    public void createComment() throws CommentException {
        CommentViewModel comment = new CommentViewModel();
        comment.setComment("test comment");
        comment.setCreatedDate(LocalDate.of(12,12,12));
        comment.setAccountId(1);
        comment.setPostId(1);


        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);


        assertEquals(comment1, serviceLayer.createComment(comment));
    }

    @Test
    public void getCommentById() throws CommentException {
        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);


        assertEquals(comment1, serviceLayer.getCommentById(comment1.getCommentId()));
    }

    @Test
    public void getCommentByAccountId() {
        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);

        List<CommentViewModel> commentList = new ArrayList<>(Collections.singleton(comment1));

        assertEquals(commentList, serviceLayer.getCommentByAccountId(comment1.getAccountId()));
    }

    @Test
    public void getCommentByPostId() {
        AccountViewModel account1 = new AccountViewModel();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        CommentViewModel comment1 = new CommentViewModel();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);

        List<CommentViewModel> commentList = new ArrayList<>(Collections.singleton(comment1));

        assertEquals(commentList, serviceLayer.getCommentByAccountId(comment1.getPostId()));
    }

    public void setUpAccountFeignMock(){
        Account account = new Account();
        account.setAccountName("test account");
        account.setCreatedDate(LocalDate.of(10,10,10));

        Account account1 = new Account();
        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        doReturn(account1).when(accountFeign).createAccount(account);
        doReturn(account1).when(accountFeign).getAccountById(account1.getAccountId());
    }

    public void setUpPostFeignMock(){
        Post post = new Post();
        post.setPost("test post");
        post.setAccountId(1);
        post.setCreatedDate(LocalDate.of(11,11,11));

        Post post1 = new Post();
        post1.setPostId(1);
        post1.setPost("test post");
        post1.setAccountId(1);
        post1.setCreatedDate(LocalDate.of(11,11,11));

        List<Post> postList = new ArrayList<>(Collections.singleton(post1));

        doReturn(post1).when(postFeign).createPost(post);
        doReturn(post1).when(postFeign).getPostById(post1.getPostId());
        doReturn(postList).when(postFeign).getAllPosts();
    }

    public void setUpCommentFeignMock(){
        Comment comment = new Comment();
        comment.setComment("test comment");
        comment.setCreatedDate(LocalDate.of(12,12,12));
        comment.setAccountId(1);
        comment.setPostId(1);

        Comment comment1 = new Comment();
        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);

        List<Comment> commentList = new ArrayList<>(Collections.singleton(comment1));

        doReturn(comment1).when(commentFeign).createComment(comment);
        doReturn(comment1).when(commentFeign).getCommentById(comment1.getCommentId());
        doReturn(commentList).when(commentFeign).getCommentsByAccountId(comment1.getAccountId());
        doReturn(commentList).when(commentFeign).getCommentsByPostId(comment1.getPostId());
        doReturn(commentList).when(commentFeign).getAllComments();
    }

}