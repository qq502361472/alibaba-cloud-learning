package com.hjrpc.storage.controller;

import com.hjrpc.api.common.CommonResult;
import com.hjrpc.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StorageController {
    @Autowired
    StorageService storageService;

    @GetMapping(value = "/decreaseStorage")
    CommonResult decreaseStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        return storageService.decreaseStorage(productId,count);
    }
}
