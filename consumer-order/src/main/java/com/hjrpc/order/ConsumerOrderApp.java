package com.hjrpc.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoDataSourceProxy
//@RibbonClient(name = "PROVIDER-PAYMENT",configuration = MyRuleConfig.class)
@EnableFeignClients("com.hjrpc.api.service")
@ComponentScan(basePackages = {"com.hjrpc.security","com.hjrpc.order","com.hjrpc.api"})
public class ConsumerOrderApp {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderApp.class, args);
    }
}
