package com.hjrpc.payment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    int decreaseAccount(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
