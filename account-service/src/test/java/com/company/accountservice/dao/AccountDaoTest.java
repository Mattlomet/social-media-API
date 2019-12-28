package com.company.accountservice.dao;

import com.company.accountservice.model.Account;
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
public class AccountDaoTest {

    @Autowired
    AccountDao accountDao;

    @Before
    public void setUp(){
        accountDao.findAll().stream().forEach(account -> {
            accountDao.deleteById(account.getAccountId());
        });
    }

    @Test
    public void createGetGetAllDeleteAccount(){
        Account account = new Account();
        account.setAccountName("Matt");
        account.setCreatedDate(LocalDate.now());

        accountDao.save(account);

        assertEquals(account,accountDao.getOne(account.getAccountId()));

        assertEquals(account,accountDao.findAll().get(0));

        accountDao.deleteById(account.getAccountId());

        assertEquals(0,accountDao.findAll().size());

    }

    @Test
    public void updateAccount(){
        Account account = new Account();
        account.setAccountName("Matt");
        account.setCreatedDate(LocalDate.now());

        accountDao.save(account);

        account.setAccountName("Matt changed");

        accountDao.save(account);

        assertEquals("Matt changed",accountDao.getOne(account.getAccountId()).getAccountName());

    }
}
