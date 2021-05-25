package com.hjrpc.payment.service;

import com.hjrpc.api.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int createPayment(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
