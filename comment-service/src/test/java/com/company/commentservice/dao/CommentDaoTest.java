package com.company.commentservice.dao;

import com.company.commentservice.model.Comment;
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
public class CommentDaoTest {

    @Autowired
    CommentDao commentDao;

    @Before
    public void setUp(){
        commentDao.findAll().stream().forEach(comment -> {
            commentDao.deleteById(comment.getCommentId());
        });
    }


    @Test
    public void createGetGetAllDeleteTest(){
        Comment comment = new Comment();
        comment.setAccountId(1);
        comment.setPostId(1);
        comment.setComment("this is a comment");
        comment.setCreatedDate(LocalDate.now());

        commentDao.save(comment);

        assertEquals(comment,commentDao.getOne(comment.getCommentId()));

        assertEquals(1,commentDao.findAll().size());

        commentDao.deleteById(comment.getCommentId());

        assertEquals(0, commentDao.findAll().size());
    }

    @Test
    public void updateTest(){
        Comment comment = new Comment();
        comment.setAccountId(1);
        comment.setPostId(1);
        comment.setComment("this is a comment");
        comment.setCreatedDate(LocalDate.now());

        commentDao.save(comment);

        comment.setPostId(2);

        commentDao.save(comment);

        assertEquals(2,commentDao.getOne(comment.getCommentId()).getPostId());
    }

    @Test
    public void getCommentByAccountId(){
        Comment comment = new Comment();
        comment.setAccountId(1);
        comment.setPostId(1);
        comment.setComment("this is a comment");
        comment.setCreatedDate(LocalDate.now());

        commentDao.save(comment);

        assertEquals(comment,commentDao.getCommentByPostId(comment.getPostId()).get(0));
    }

    @Test
    public void getCommentByPostId(){
        Comment comment = new Comment();
        comment.setAccountId(1);
        comment.setPostId(1);
        comment.setComment("this is a comment");
        comment.setCreatedDate(LocalDate.now());

        commentDao.save(comment);

        assertEquals(comment,commentDao.getCommentByAccountId(comment.getAccountId()).get(0));
    }
}
