package com.nazarati.config;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.AerospikeException;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Log;
import com.aerospike.client.Operation;
import com.aerospike.client.Value;
import com.aerospike.client.cdt.ListOperation;
import com.aerospike.client.cdt.ListOrder;
import com.aerospike.client.cdt.ListPolicy;
import com.aerospike.client.cdt.ListWriteFlags;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {

    int counter = 0;
    String userA = "a";
    String userB = "b";
    String lastSenderName = userA;

    @KafkaListener(id = "message-listener", topics = "messages")
    public void listen(String data) {
        try {
            System.out.println(data);

            Log.setCallbackStandard();
            var policy = new ClientPolicy();
            policy.user = "admin";
            policy.password = "admin";

            var client = new AerospikeClient("127.0.0.1", 3000);
            var writePolicy = new WritePolicy();
            writePolicy.sendKey = true;

            var listPolicy = new ListPolicy(ListOrder.ORDERED, ListWriteFlags.INSERT_BOUNDED);

            var key = new Key("tinychat", "messages", counter++);

            var lastSender = new Bin("last_sender", lastSenderName);

            lastSenderName = lastSenderName.equals(userA) ? userB : userA;

            var x = client.operate(writePolicy,
                    key,
                    Operation.put(lastSender),
                    ListOperation.append(listPolicy, "msg_chain", Value.get(data)),
                    Operation.get()
            );
            System.out.println();
            client.close();
        }
        catch (AerospikeException e) {
            throw new RuntimeException(e);
        }
    }

}
