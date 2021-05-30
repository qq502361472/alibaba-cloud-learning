package com.hjrpc.payment.service;

import com.hjrpc.api.common.CommonResult;

import java.math.BigDecimal;

public interface AccountService {
    CommonResult decreaseAccount(Long userId, BigDecimal money);
}
