package com.nazarati.core;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor extends LoggedClass<MessageProcessor> {

    public MessageProcessor() {
        super(MessageProcessor.class);
    }

    @KafkaListener(id = "message-listener", topics = "${tinychat.kafka.main-topic}")
    public void listen(String data) {
        logger.trace("began processing of message [{}]", data);
        logger.trace("completed processing of message [{}]", data);
    }

}
