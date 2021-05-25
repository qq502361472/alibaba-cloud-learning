package com.hjrpc.order.controller;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import com.hjrpc.api.service.FeignPaymentService;
import com.hjrpc.order.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope//不能加到配置类上
@Slf4j
public class FeignOrderController {
    @Autowired
    FeignPaymentService feignPaymentService;
    @Value("${project.info}")
    String projectInfo;

    @Autowired
    MQService mqService;

    @PostMapping("/feign/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        return feignPaymentService.createPayment(payment);
    }

    @GetMapping(value = "/feign/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("*****order:"+projectInfo);
        return feignPaymentService.getPaymentById(id);
    }

    @GetMapping(value = "/feign/discovery")
    public CommonResult discoveryClient() {
        return feignPaymentService.discoveryClient();
    }

    @GetMapping(value = "/hystrix/{type}")
    public CommonResult hystrixTest1(@PathVariable("type") String type) {
        System.out.println("order:"+projectInfo);
        simulateByType(type);
        return CommonResult.success("success");
    }
    @GetMapping(value = "/message/{message}")
    public CommonResult sendMessage(@PathVariable("message") String message) {
        boolean b = mqService.sendMessage(message);
        if(b){
            return CommonResult.success("send message["+message+"] success!");
        }
        return CommonResult.failed("send message["+message+"]  failure!");
    }



    private void simulateByType(@PathVariable("type") String type) {
        if (StringUtils.equals(type, "timeout")) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (StringUtils.equals(type, "error")) {
            throw new RuntimeException("出现错误了！");
        }
    }

    @GetMapping(value = "/hystrix2/{type}")
    public CommonResult hystrixTest2(@PathVariable("type") String type) {
        System.out.println("order:"+projectInfo);
        simulateByType(type);
        return CommonResult.success("success");
    }

    @GetMapping(value = "/hystrixFeign/{type}")
    public CommonResult hystrixTestFeign(@PathVariable("type") String type) {
        System.out.println("order:"+projectInfo);
        return feignPaymentService.hystrixTest(type);
    }

    @GetMapping(value = "/circuitBreaker/{type}")
    public CommonResult circuitBreaker(@PathVariable("type") String type) {
        simulateByType(type);
        return CommonResult.success("success");
    }
    public CommonResult circuitBreakerFallback(@PathVariable("type") String type) {
        return CommonResult.demotionFailed("counsumercircuitBreaker服务降级");
    }

    public CommonResult hystrixTestFallbackHandler(){
        return CommonResult.demotionFailed("counsumer服务降级");
    }
}
