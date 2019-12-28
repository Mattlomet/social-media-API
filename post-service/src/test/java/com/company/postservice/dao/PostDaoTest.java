package com.company.postservice.dao;

import com.company.postservice.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class PostDaoTest {

    @Autowired
    PostDao postDao;

    @Before
    public void setUp(){
        postDao.findAll().stream().forEach(post -> {
            postDao.deleteById(post.getPostId());
        });
    }

    @Test
    public void createGetGetAllTest(){
        Post post = new Post();
        post.setAccountId(1);
        post.setCreatedDate(LocalDate.now());
        post.setPost("this is a post");

        postDao.save(post);

        assertEquals(post,postDao.getOne(post.getPostId()));

        assertEquals(1,postDao.findAll().size());

        postDao.deleteById(post.getPostId());

        assertEquals(0,postDao.findAll().size());
    }

    @Test
    public void updateTest(){
        Post post = new Post();
        post.setAccountId(1);
        post.setCreatedDate(LocalDate.now());
        post.setPost("this is a post");

        postDao.save(post);

        post.setPost("this is the new post");

        postDao.save(post);

        assertEquals("this is the new post",postDao.getOne(post.getPostId()).getPost());

    }

    @Test
    public void getPostsByAccountId(){
        Post post = new Post();
        post.setAccountId(1);
        post.setCreatedDate(LocalDate.now());
        post.setPost("this is a post");

        postDao.save(post);


        assertEquals(post,postDao.getPostsByAccountId(post.getAccountId()).get(0));

    }
}
