package com.hjrpc.order.controller;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Order;
import com.hjrpc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeataOrderController {

    @Autowired
    OrderService orderService;

    //userId=1&productId=1&count=10&money=100
    @GetMapping(value = "/createOrder")
    public CommonResult createOrder(Order order){
        //设置订单状态为待支付
        order.setStatus(0);
        int result = orderService.createOrder(order);
        if(result>0){
            return CommonResult.success(order);
        }
        return CommonResult.failed("创建订单失败");
    }

    @GetMapping(value = "/payOrder/{orderID}")
    public CommonResult payOrder(@PathVariable(value = "orderID") Long orderID){
        return orderService.payOrder(orderID);
    }
}
