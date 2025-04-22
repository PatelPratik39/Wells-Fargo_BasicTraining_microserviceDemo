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
    private final ObjectMapper mapper;
    private static final String PRODUCT_TOPIC = "product-topic";

    public void sendProductMessage(String message) {
        kafkaTemplate.send(PRODUCT_TOPIC, message);
    }
}

//    public void sendProductCreatedEvent(Product product) {
//        sendEvent("product-created-topic", product);
//    }
//
//    public void sendProductUpdatedEvent(Product product) {
//        sendEvent("product-updated-topic", product);
//    }
//
//    public void sendProductDeletedEvent(Product product) {
//        sendEvent("product-deleted-topic", product);
//    }
//
//    private void sendEvent(String topic, Product product) {
//        try {
//            String message = mapper.writeValueAsString(product);
//            kafkaTemplate.send(topic, message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}