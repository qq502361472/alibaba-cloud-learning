package com.hjrpc.order.service.impl;

import com.hjrpc.order.service.MQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
@Slf4j
public class MQServiceImpl implements MQService {

    @Autowired
    @Qualifier("output")
    MessageChannel messageChannel;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public boolean sendMessage(String message) {
        log.info("consumer order port[{}] send message :{}", serverPort, message);
        return messageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
