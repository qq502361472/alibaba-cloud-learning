package com.hjrpc.order.service;

public interface MQService {
    public boolean sendMessage(String message);
}
