package com.nazarati.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value(value = "localhost:9092")
    private String bootstrapAddress;

    // TODO: when local profile is introduced also add programmatic topic creation for convenience

    @Bean
    Map<String, Object> consumerConfigs() {
        try {
            var props = new HashMap<String, Object>();
            props.put("client.id", InetAddress.getLocalHost().getHostName());
            props.put("group.id", "test-consumer-group");
            props.put("bootstrap.servers", "localhost:9092");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

            return props;
        }
        catch (UnknownHostException e) {
            throw new RuntimeException("failed to set kafka properties");
        }

    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<Integer, String>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));

        return factory;
    }

}
