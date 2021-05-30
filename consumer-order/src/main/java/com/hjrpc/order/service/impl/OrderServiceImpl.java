package com.hjrpc.order.service.impl;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.api.entity.Order;
import com.hjrpc.api.service.FeignPaymentService;
import com.hjrpc.api.service.SeataStorageService;
import com.hjrpc.order.dao.OrderDao;
import com.hjrpc.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    FeignPaymentService feignPaymentService;
    @Autowired
    SeataStorageService seataStorageService;

    @Override
    public int createOrder(Order order) {
        return orderDao.createOrder(order);
    }

    @Override
    @GlobalTransactional
    public CommonResult payOrder(Long orderID) {
        // 查询订单
        Order order = orderDao.getOrderByID(orderID);
        if (1 == order.getStatus()) {
            throw new RuntimeException("订单已经被支付");
        }
        //更新订单状态为已支付
        int res = orderDao.updateOrderStatus(orderID);
        if (res < 0) {
            throw new RuntimeException("订单状态更新失败");
        }
        //订单进行支付
        CommonResult accountResult = feignPaymentService.decreaseAccount(order.getUserId(), order.getMoney());
        if (200 != accountResult.getCode()) {
            throw new RuntimeException(accountResult.getMessage());
        }
        //订单扣减库存
        CommonResult storageResult = seataStorageService.decreaseStorage(order.getProductId(), order.getCount());
        if (200 != storageResult.getCode()) {
            throw new RuntimeException(storageResult.getMessage());
        }
        System.out.println(1/0);
        return CommonResult.success(order);
    }
}
