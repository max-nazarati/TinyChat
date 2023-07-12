package com.nazarati.config;

import com.nazarati.core.Pojo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${tinychat.kafka.bootstrap-server}")
    private String bootstrapAddress;

    // TODO: when local profile is introduced also add programmatic topic creation for convenience

    @Bean
    Map<String, Object> consumerConfigs() {
        try {
            var props = new HashMap<String, Object>();
            props.put("client.id", InetAddress.getLocalHost().getHostName());
            props.put("group.id", "test-consumer-group");
            props.put("bootstrap.servers", bootstrapAddress);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());

            return props;
        } catch (UnknownHostException e) {
            throw new RuntimeException("failed to set kafka properties");
        }

    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, Pojo>> kafkaListenerContainerFactory() {
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<Integer, Pojo>();
        containerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new IntegerDeserializer(),
                new JsonDeserializer<>(Pojo.class, false)
        ));

        return containerFactory;
    }

}
