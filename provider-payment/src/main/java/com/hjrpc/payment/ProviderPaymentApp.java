package com.hjrpc.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.hjrpc.security","com.hjrpc.payment"})
public class ProviderPaymentApp {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApp.class,args);
    }
}
