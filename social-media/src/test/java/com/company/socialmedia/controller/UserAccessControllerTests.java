package com.company.socialmedia.controller;


import com.company.socialmedia.service.ServiceLayer;
import com.company.socialmedia.util.feign.AccountFeign;
import com.company.socialmedia.util.feign.CommentFeign;
import com.company.socialmedia.util.feign.PostFeign;
import com.company.socialmedia.viewmodel.AccountViewModel;
import com.company.socialmedia.viewmodel.CommentViewModel;
import com.company.socialmedia.viewmodel.PostViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(SocialController.class)
public class UserAccessControllerTests {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    ServiceLayer serviceLayer;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    AccountFeign accountFeign;

    @MockBean
    CommentFeign commentFeign;

    @MockBean
    PostFeign postFeign;

    @Autowired
    ObjectMapper mapper;


    AccountViewModel avm = new AccountViewModel();
    AccountViewModel avm1 = new AccountViewModel();

    PostViewModel post = new PostViewModel();
    PostViewModel post1 = new PostViewModel();

    AccountViewModel account = new AccountViewModel();
    AccountViewModel account1 = new AccountViewModel();

    CommentViewModel comment = new CommentViewModel();
    CommentViewModel comment1 = new CommentViewModel();


    @Before
    public void setUp() throws Exception {
        avm.setAccountName("test account");
        avm.setCreatedDate(LocalDate.of(10,10,10));

        avm1.setAccountId(1);
        avm1.setAccountName("test account");
        avm1.setCreatedDate(LocalDate.of(10,10,10));


        post.setPost("test post");
        post.setAccountId(1);
        post.setCreatedDate(LocalDate.of(11,11,11));

        post1.setPostId(1);
        post1.setPost("test post");
        post1.setAccountId(1);
        post1.setCreatedDate(LocalDate.of(11,11,11));

        account.setAccountName("test account");
        account.setCreatedDate(LocalDate.of(10,10,10));

        account1.setAccountId(1);
        account1.setAccountName("test account");
        account1.setCreatedDate(LocalDate.of(10,10,10));

        post1.setAccountViewModel(account1);

        comment.setComment("test comment");
        comment.setCreatedDate(LocalDate.of(12,12,12));
        comment.setAccountId(1);
        comment.setPostId(1);

        comment1.setCommentId(1);
        comment1.setComment("test comment");
        comment1.setCreatedDate(LocalDate.of(12,12,12));
        comment1.setAccountId(1);
        comment1.setPostId(1);
        comment1.setAccountViewModel(account1);

        List<CommentViewModel> commentList = new ArrayList<>(Collections.singleton(comment1));

        post1.setCommentList(commentList);

        setUpServiceLayerMock();
    }

    // account controllers

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void createAccountTest() throws Exception {
        String input = mapper.writeValueAsString(account);
        String output = mapper.writeValueAsString(account1);

        this.mockmvc.perform(post("/account")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(output));
    }

    @Test
    public void createAccountTestError() throws Exception {
        String input = mapper.writeValueAsString(account);

        this.mockmvc.perform(post("/account")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void getAccountByIdTest() throws Exception {
        String output = mapper.writeValueAsString(account1);

        this.mockmvc.perform(get("/account/1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(output));
    }

    @Test
    public void getAccountByIdTestError() throws Exception {
        this.mockmvc.perform(get("/account/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void updateAccountTest() throws Exception {
        String input = mapper.writeValueAsString(account);

        this.mockmvc.perform(put("/account")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateAccountTestError() throws Exception {
        String input = mapper.writeValueAsString(account);

        this.mockmvc.perform(put("/account")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteAccountByIdTest() throws Exception {

        this.mockmvc.perform(delete("/account/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAccountByIdTestError() throws Exception {
        this.mockmvc.perform(delete("/account/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    // post controllers


    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void createPostTest() throws Exception {
        String input = mapper.writeValueAsString(post);
        String output = mapper.writeValueAsString(post1);

        this.mockmvc.perform(post("/post")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(output));
    }

    @Test
    public void createPostTestError() throws Exception {
        String input = mapper.writeValueAsString(post);

        this.mockmvc.perform(post("/post")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void getPostByIdTest() throws Exception {
        String output = mapper.writeValueAsString(post1);

        this.mockmvc.perform(get("/post/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(output));
    }

    @Test
    public void getPostByIdTestError() throws Exception {
        this.mockmvc.perform(get("/post/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void getAllPostsTest() throws Exception {
        List<PostViewModel> postViewModelList = new ArrayList<>(Collections.singleton(post1));
        String output = mapper.writeValueAsString(postViewModelList);

        this.mockmvc.perform(get("/post")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(output));
    }

    @Test
    public void getAllPostsTestError() throws Exception {
        this.mockmvc.perform(get("/post")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void updatePostTest() throws Exception {
        String input = mapper.writeValueAsString(post1);

        this.mockmvc.perform(put("/post")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updatePostTestError() throws Exception {
        String input = mapper.writeValueAsString(post1);

        this.mockmvc.perform(put("/post")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deletePostById() throws Exception {

        this.mockmvc.perform(delete("/post/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deletePostByIdError() throws Exception {
        this.mockmvc.perform(delete("/post/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


    // comment controllers

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void createCommentTest() throws Exception {
        String input = mapper.writeValueAsString(comment);
        String output = mapper.writeValueAsString(comment1);

        this.mockmvc.perform(post("/comment")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(output));
    }

    @Test
    public void createCommentTestError() throws Exception {
        String input = mapper.writeValueAsString(comment);

        this.mockmvc.perform(post("/comment")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void getCommentByIdTest() throws Exception {
        String output = mapper.writeValueAsString(comment1);

        this.mockmvc.perform(get("/comment/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(output));
    }

    @Test
    public void getCommentByIdTestError() throws Exception {
        this.mockmvc.perform(get("/comment/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER","ROLE_ADMIN"})
    public void updateCommentTest() throws Exception {
        String input = mapper.writeValueAsString(comment1);

        this.mockmvc.perform(put("/comment")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateCommentTestError() throws Exception {
        String input = mapper.writeValueAsString(comment1);

        this.mockmvc.perform(put("/comment")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteCommentById() throws Exception {

        this.mockmvc.perform(delete("/comment/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCommentByIdError() throws Exception {
        this.mockmvc.perform(delete("/comment/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }



    public void setUpServiceLayerMock() throws Exception {

        // account mocks
        doReturn(account1).when(serviceLayer).createAccount(account);
        doReturn(account1).when(serviceLayer).getAccountById(1);

        // post mocks
        List<PostViewModel> postViewModelList = new ArrayList<>(Collections.singleton(post1));
        doReturn(post1).when(serviceLayer).createPost(post);
        doReturn(post1).when(serviceLayer).getPostById(1);
        doReturn(postViewModelList).when(serviceLayer).getAllPosts();

        // comment mocks
        List<CommentViewModel> commentViewModelList = new ArrayList<>(Collections.singleton(comment1));
        doReturn(comment1).when(serviceLayer).createComment(comment);
        doReturn(comment1).when(serviceLayer).getCommentById(1);
        doReturn(commentViewModelList).when(serviceLayer).getCommentByPostId(1);


    }

}
