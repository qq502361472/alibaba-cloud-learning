package com.hjrpc.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@RibbonClient(name = "PROVIDER-PAYMENT",configuration = MyRuleConfig.class)
@EnableFeignClients("com.hjrpc.api.service")
@ComponentScan(basePackages = {"com.hjrpc.security","com.hjrpc.order","com.hjrpc.api"})
public class ConsumerOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApp.class, args);
    }
}
