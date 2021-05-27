package com.hjrpc.order.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Payment;
import org.springframework.web.bind.annotation.PathVariable;

public class FeignPaymentBlockHandler {

    public static CommonResult<Payment>  getPaymentByIdBlock(@PathVariable("id") Long id, BlockException e){
        return CommonResult.demotionFailed("限流了");
    }
}
