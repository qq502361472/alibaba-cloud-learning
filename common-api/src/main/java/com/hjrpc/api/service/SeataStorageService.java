package com.hjrpc.api.service;


import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "provider-storage", configuration = FeignConfig.class,fallback = SeataStorageServiceFallback.class)
public interface SeataStorageService {
    @GetMapping(value = "/decreaseStorage")
    CommonResult decreaseStorage(@RequestParam("productId") Long productId, @RequestParam("count")Integer count);
}
