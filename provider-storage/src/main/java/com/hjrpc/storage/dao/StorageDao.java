package com.hjrpc.storage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {
    int decreaseStorage(@Param("productId") Long productId, @Param("count")Integer count);
}
