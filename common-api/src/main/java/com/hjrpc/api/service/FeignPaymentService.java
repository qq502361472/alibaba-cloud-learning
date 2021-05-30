package com.hjrpc.api.service;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.config.FeignConfig;
import com.hjrpc.api.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Component
@FeignClient(name = "provider-payment"
        , configuration = FeignConfig.class
//        , fallback = FeignPaymentServiceFallback.class
        , fallbackFactory = FeignPaymentServiceFallbackFactory.class
)
public interface FeignPaymentService {
    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment);

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/discovery")
    public CommonResult discoveryClient();

    @GetMapping(value = "/hystrix/{type}")
    public CommonResult hystrixTest(@PathVariable("type") String type);

    @GetMapping(value = "/decreaseAccount")
    CommonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
