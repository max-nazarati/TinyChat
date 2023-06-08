package com.nazarati.config;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

    @KafkaListener(id = "message-listener", topics = "messages")
    public void listen(String data) {
        System.out.println(data);
        System.out.println();
    }

}
