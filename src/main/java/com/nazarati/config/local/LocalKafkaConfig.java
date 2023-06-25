package com.nazarati.config.local;

import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
@Profile("local")
public class LocalKafkaConfig {

    @Bean
    KafkaAdmin.NewTopics topics(@Value("${tinychat.kafka.main-topic}") String mainTopic) {

        return new KafkaAdmin.NewTopics(TopicBuilder.name(mainTopic)
                .partitions(1)
                .replicas(1)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build());
    }
}
