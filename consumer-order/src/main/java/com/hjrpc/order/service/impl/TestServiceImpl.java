package com.hjrpc.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hjrpc.api.common.CommonResult;
import com.hjrpc.order.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @SentinelResource(value = "testChain",blockHandler = "testChainBlock")
    public CommonResult testChain(String from) {
        return CommonResult.success("成功访问:"+from);
    }

    public CommonResult testChainBlock(String from){
        return CommonResult.failed("链路流控,访问失败");
    }
}
