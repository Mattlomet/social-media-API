package com.company.queueconsumerservice.util.feign;

import com.company.queueconsumerservice.util.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service")
public interface AccountFeign {

    //delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccountById(@PathVariable int id);

}
