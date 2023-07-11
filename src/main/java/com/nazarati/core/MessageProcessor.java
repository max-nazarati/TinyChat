package com.nazarati.core;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Log;
import com.aerospike.client.Record;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.WritePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor extends LoggedClass<MessageProcessor> {

    public MessageProcessor() {
        super(MessageProcessor.class);
    }

    int i = 0;

    @KafkaListener(id = "message-listener", topics = "${tinychat.kafka.main-topic}")
    public void listen(Pojo data) {
        logger.trace("began processing of message [{}]", data);
        Log.setCallbackStandard();
        Log.setLevel(Log.Level.INFO);
        var client = new AerospikeClient("127.0.0.1", 3000);
        var writePolicy = new WritePolicy();
        writePolicy.sendKey = true;
        var key = new Key("tinychat", "messages", i);
        i = i == 0 ? 1 : 0;
        var message = new Bin("history", data);

        client.put(writePolicy, key, message);
        client.close();

        client = new AerospikeClient("127.0.0.1", 3000);
        var readPolicy = new Policy();
        Record record = client.get(readPolicy, key);

        logger.trace("completed processing of message [{}]", data);
    }

}
