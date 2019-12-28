package com.company.socialmedia.util.feign;

import com.company.socialmedia.model.Account;
import com.company.socialmedia.util.fallback.AccountFallBack;
import com.company.socialmedia.viewmodel.AccountViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service",fallback = AccountFallBack.class)
public interface AccountFeign {

    //post
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    Account createAccount(@RequestBody Account account);

    //read
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Account getAccountById(@PathVariable int id);

}
