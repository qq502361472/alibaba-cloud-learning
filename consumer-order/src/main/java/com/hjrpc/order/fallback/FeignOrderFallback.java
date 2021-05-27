package com.hjrpc.order.fallback;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;

public class FeignOrderFallback {

    public static CommonResult testSentinel(String type, Throwable e) {
        return CommonResult.failed("服务出现异常,公共降级方法执行:");
    }

    public static CommonResult<Payment> getPaymentByIdFallback(Long id, Throwable e) {
        return CommonResult.failed("服务出现异常,降级方法执行:");
    }
}
