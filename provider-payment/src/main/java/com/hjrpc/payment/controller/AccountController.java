package com.hjrpc.payment.controller;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.payment.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/decreaseAccount")
    public CommonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        return accountService.decreaseAccount(userId,money);
    }
}
