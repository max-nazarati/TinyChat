package com.nazarati.core.consumer;

import com.nazarati.core.Pojo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class Factory {
    @Bean
    public ConsumerFactory<String, Pojo> pojoConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(Map.of(
                ConsumerConfig.CLIENT_ID_CONFIG, "factoriedConsumer",
                ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group",
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, new StringDeserializer(),
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new JsonDeserializer<Pojo>()
        ));
    }

}
