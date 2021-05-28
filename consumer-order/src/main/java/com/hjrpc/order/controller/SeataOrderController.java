package com.hjrpc.order.controller;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeataOrderController {


    @GetMapping
    public CommonResult payOrder(Order order){
        return null;
    }
}
