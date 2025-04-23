package com.wf.productservice.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductKafkaConsumer {

    @KafkaListener(topics = "product-topic", groupId = "product-group")
    public void consume(String message){
        log.info("Consumed product event: {}", message);
//        OR
        System.out.println("Received message: " + message);
    }
}