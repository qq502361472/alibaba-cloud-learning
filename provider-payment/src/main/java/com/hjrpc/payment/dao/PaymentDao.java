package com.hjrpc.payment.dao;

import com.hjrpc.api.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int createPayment(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
