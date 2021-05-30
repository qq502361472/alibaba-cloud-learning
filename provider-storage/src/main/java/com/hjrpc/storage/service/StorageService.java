package com.hjrpc.storage.service;

import com.hjrpc.api.common.CommonResult;

public interface StorageService {
    CommonResult decreaseStorage(Long productId, Integer count);
}
