package com.nazarati.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

    @KafkaListener(id = "message-listener", topics = "${tinychat.kafka.main-topic}")
    public void listen(String data) {
        logger.trace("begin processing of message [{}]", data);
    }

}
