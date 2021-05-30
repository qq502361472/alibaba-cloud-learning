package com.hjrpc.payment.service.impl;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.payment.dao.AccountDao;
import com.hjrpc.payment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public CommonResult decreaseAccount(Long userId, BigDecimal money) {
        int res = accountDao.decreaseAccount(userId, money);
        if(res==1){
            return CommonResult.success("账户扣减成功");
        }
        return CommonResult.failed("账户扣减失败");
    }
}
