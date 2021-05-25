package com.hjrpc.order.controller;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;

    public static final String PaymentSrv_URL = "http://PROVIDER-PAYMENT";

    @PostMapping("/order/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        return restTemplate.postForObject(PaymentSrv_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/order/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/"+id, CommonResult.class, id);
    }

    @GetMapping(value = "/order/payment/discovery")
    public CommonResult discoveryClient() {
        return restTemplate.getForObject(PaymentSrv_URL + "/payment/discovery", CommonResult.class);
    }
}
