package com.company.accountservice.controller;


import com.company.accountservice.dao.AccountDao;
import com.company.accountservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    //post
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account){
        return accountDao.save(account);
    }

    //read
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable int id){
        return accountDao.getOne(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(){
        return accountDao.findAll();
    }


    //update
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@RequestBody Account account){
        accountDao.save(account);
    }

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccountById(@PathVariable int id){
         accountDao.deleteById(id);
    }
}
