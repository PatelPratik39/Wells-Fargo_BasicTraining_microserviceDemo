package com.wf.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "user-order-topic", groupId = "order-group")
    public void consumeMessage(String message) {

        System.out.println("Consumed message: " + message);
    }
}