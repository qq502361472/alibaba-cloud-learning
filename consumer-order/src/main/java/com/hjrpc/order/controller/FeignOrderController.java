package com.hjrpc.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import com.hjrpc.api.service.FeignPaymentService;
import com.hjrpc.api.service.FeignPaymentServiceFallback;
import com.hjrpc.order.block.FeignPaymentBlockHandler;
import com.hjrpc.order.fallback.FeignOrderFallback;
import com.hjrpc.order.service.MQService;
import com.hjrpc.order.service.TestService;
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
    @Autowired
    TestService testService;
    @Value("${project.info}")
    String projectInfo;

    @Autowired
    MQService mqService;

    @PostMapping("/feign/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        return feignPaymentService.createPayment(payment);
    }

    @GetMapping(value = "/feign/get/{id}")
    @SentinelResource(value = "feign.get",
            fallbackClass = FeignOrderFallback.class,
            fallback = "getPaymentByIdFallback",
            blockHandlerClass = FeignPaymentBlockHandler.class,
            blockHandler = "getPaymentByIdBlock",
            exceptionsToIgnore = NullPointerException.class)//对某种异常不进行fallback
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("*****order:" + projectInfo);
        if (id > 4) {
            throw new RuntimeException("id过大了yo");
        } else if (id < 1) {
            throw new NullPointerException("空");
        }
        return feignPaymentService.getPaymentById(id);
    }

    @GetMapping(value = "/feign/discovery")
    public CommonResult discoveryClient() {
        return feignPaymentService.discoveryClient();
    }

    @GetMapping(value = "/testChainA")
    public CommonResult testChainA() {

        return testService.testChain("AA");
    }

    @GetMapping(value = "/testChainB")
    public CommonResult testChainB() {

        return testService.testChain("BB");
    }

    @GetMapping(value = "/message/{message}")
    public CommonResult sendMessage(@PathVariable("message") String message) {
        boolean b = mqService.sendMessage(message);
        if (b) {
            return CommonResult.success("send message[" + message + "] success!");
        }
        return CommonResult.failed("send message[" + message + "]  failure!");
    }

    @GetMapping("/testSF/{type}")
    @SentinelResource(fallbackClass = FeignOrderFallback.class, fallback = "testSentinel")
    public CommonResult testSentinelFallback(@PathVariable("type") String type) {
        simulateByType(type);
        return CommonResult.success("testSentinelFallback成功");
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
}
