package com.hjrpc.payment.controller;

import com.alibaba.druid.util.StringUtils;
import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import com.hjrpc.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RefreshScope//不能加到配置类上
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    String curPort;

    @Value("${project.info}")
    String projectInfo;

    @PostMapping("/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        System.out.println("payment:"+projectInfo);
        int result = paymentService.createPayment(payment);
        log.info("插入返回结果:{}", result);
        if (result < 0) {
            return CommonResult.failed("插入数据库失败");
        }
        return CommonResult.success(payment, curPort);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("*****payment:"+projectInfo);
        Payment payment = paymentService.getPaymentById(id);

//        log.info("*****查询结果:{} ", payment);
        return CommonResult.success(payment, curPort);
    }

    @GetMapping(value = "/payment/discovery")
    public CommonResult discoveryClient() {
        discoveryClient.getServices().stream().forEach(System.out::println);
        discoveryClient.getServices().stream().forEach(x -> System.out.println(discoveryClient.getInstances(x)));
        return CommonResult.success(discoveryClient.toString(), curPort);
    }

    @GetMapping(value = "/hystrix/{type}")
    public CommonResult hystrixTest(@PathVariable("type") String type) {
        System.out.println("payment:"+projectInfo);
        simulateByType(type);
        return CommonResult.success("success");
    }

    private void simulateByType(@PathVariable("type") String type) {
        if (StringUtils.equals(type, "timeout")) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
            }
        } else if (StringUtils.equals(type, "error")) {
            throw new RuntimeException("出现错误了！");
        }else if (StringUtils.equals(type, "shortTimeout")) {
            try {
                Thread.sleep(2500L);
            } catch (InterruptedException e) {
            }
        }
    }

    public CommonResult hystrixTestFallbackHandler(String type){
        return CommonResult.demotionFailed();
    }
}
