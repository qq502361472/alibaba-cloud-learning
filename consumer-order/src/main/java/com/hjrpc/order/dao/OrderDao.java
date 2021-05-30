package com.hjrpc.order.dao;

import com.hjrpc.api.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    int createOrder(Order order);
    int updateOrderStatus(Long orderID);

    Order getOrderByID(String orderID);
}
