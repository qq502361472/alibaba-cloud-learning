package com.hjrpc.order.service.impl;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Order;
import com.hjrpc.order.dao.OrderDao;
import com.hjrpc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @Override
    public CommonResult payOrder(String orderID) {
        // 查询订单
        Order order =  orderDao.getOrderByID(orderID);

        //更新订单状态为已支付

        //订单进行支付

        //订单扣减库存

        return CommonResult.success(order);
    }
}
