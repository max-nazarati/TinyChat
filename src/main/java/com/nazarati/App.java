package com.nazarati;

import com.nazarati.config.MessageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.UnknownHostException;

@SpringBootApplication
public class App
{
    @Autowired
    MessageProcessor processor;

    public static void main(String[] args) throws UnknownHostException
    {
//        Properties config = new Properties();
//        config.put("client.id", InetAddress.getLocalHost().getHostName());
//        config.put("group.id", "test-consumer-group");
//        config.put("bootstrap.servers","localhost:9092");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//
//        var consumer = new KafkaConsumer<String, String>(config);
//        consumer.subscribe(List.of("test-messages"));
//        System.out.println("SERVER INITIALISED");
//
//        while(true) {
//            var records = consumer.poll(Duration.ofMillis(500));
//            records.forEach(x -> System.out.println(x.value()));
////            System.out.println("===");
//        }
        SpringApplication.run(App.class, args);
    }
}
