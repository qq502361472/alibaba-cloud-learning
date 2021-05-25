package com.hjrpc.rule;

import com.hjrpc.order.config.MyRdRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRuleConfig {

    @Bean
    public IRule rule(){
        return new RandomRule();
    }
}
