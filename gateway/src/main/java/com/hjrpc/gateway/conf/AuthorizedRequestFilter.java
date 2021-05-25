package com.hjrpc.gateway.conf;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Base64;

@Component
public class AuthorizedRequestFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String auth = "root:root"; // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
        String authHeader = "Basic " + new String(encodedAuth);

        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                .header("Authorization", authHeader).build();
        ServerWebExchange webExchange = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(webExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
