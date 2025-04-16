package com.wf.userservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "user-topic";

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}