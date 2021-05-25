package com.hjrpc.api.service;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class FeignPaymentServiceFallback implements FeignPaymentService {
    @Override
    public CommonResult createPayment(Payment payment) {
        return CommonResult.demotionFailed("feign服务降级");
    }

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        return CommonResult.demotionFailed("feign服务降级");
    }

    @Override
    public CommonResult discoveryClient() {
        return CommonResult.demotionFailed("feign服务降级");
    }

    @Override
    public CommonResult hystrixTest(String type) {
        return CommonResult.demotionFailed("feign服务降级");
    }
}
