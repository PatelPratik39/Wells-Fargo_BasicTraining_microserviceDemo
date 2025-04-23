package com.wf.productservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String PRODUCT_TOPIC = "product-topic";

    public void sendProductMessage(String message) {
        kafkaTemplate.send(PRODUCT_TOPIC, message);
    }

}