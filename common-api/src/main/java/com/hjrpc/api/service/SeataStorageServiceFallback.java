package com.hjrpc.api.service;

import com.hjrpc.api.common.CommonResult;
import org.springframework.stereotype.Component;

@Component
public class SeataStorageServiceFallback implements SeataStorageService{
    @Override
    public CommonResult decreaseStorage(Long productId, Integer count) {
        return CommonResult.demotionFailed("feign服务降级");
    }
}
