package com.hjrpc.order.service;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Order;

public interface OrderService {
    int createOrder(Order order);

    CommonResult payOrder(String orderID);
}
