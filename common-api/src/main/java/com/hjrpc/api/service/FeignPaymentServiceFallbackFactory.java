package com.hjrpc.api.service;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignPaymentServiceFallbackFactory implements FallbackFactory<FeignPaymentService> {
    @Override
    public FeignPaymentService create(Throwable throwable) {
        return new FeignPaymentService() {
            @Override
            public CommonResult createPayment(Payment payment) {
                return CommonResult.demotionFailed("feignFactory服务降级");
            }

            @Override
            public CommonResult<Payment> getPaymentById(Long id) {
                return CommonResult.demotionFailed("feignFactory服务降级");
            }

            @Override
            public CommonResult discoveryClient() {
                return CommonResult.demotionFailed("feignFactory服务降级");
            }

            @Override
            public CommonResult hystrixTest(String type) {
                return CommonResult.demotionFailed("feignFactory服务降级");
            }
        };
    }
}
