package com.wf.userservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.awt.image.ImageProducer;

@Service
public class KafkaProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    private final String TOPIC = "user-Microservice";

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}