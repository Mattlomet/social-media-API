package com.company.socialmedia.util.fallback;

import com.company.socialmedia.model.Account;
import com.company.socialmedia.util.feign.AccountFeign;
import com.netflix.hystrix.contrib.javanica.exception.FallbackInvocationException;
import org.springframework.stereotype.Component;

@Component
public class AccountFallBack implements AccountFeign {

    @Override
    public Account createAccount(Account account) {
        Account returnAccount = new Account();
        returnAccount.setAccountId(0);
        returnAccount.setAccountName("there has been a problem with getting account info");
        returnAccount.setCreatedDate(null);
        return account;
    }

    @Override
    public Account getAccountById(int id) {
        Account account = new Account();
        account.setAccountId(0);
        account.setAccountName("there has been a problem with getting account info");
        account.setCreatedDate(null);
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        System.out.println("error in - Account update");
        throw new FallbackInvocationException();
    }

}
