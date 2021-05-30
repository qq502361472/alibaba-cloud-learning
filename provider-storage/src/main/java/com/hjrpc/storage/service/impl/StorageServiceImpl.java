package com.hjrpc.storage.service.impl;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.storage.dao.StorageDao;
import com.hjrpc.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageDao storageDao;

    @Override
    public CommonResult decreaseStorage(Long productId, Integer count) {
        int res = storageDao.decreaseStorage(productId, count);
        if (res == 1) {
            return CommonResult.success("库存扣减成功");
        }
        return CommonResult.failed("库存扣减失败");
    }
}
