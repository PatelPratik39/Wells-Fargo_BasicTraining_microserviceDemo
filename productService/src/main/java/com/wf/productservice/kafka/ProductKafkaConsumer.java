package com.wf.productservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.productservice.dto.ProductDTO;
import com.wf.productservice.entity.Product;
import com.wf.productservice.mapper.ProductMapper;
import com.wf.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductKafkaConsumer {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @KafkaListener(topics = "product-topic", groupId = "product-group", containerFactory = "productKafkaListenerContainerFactory")
    public void consumeProduct(ProductDTO productDTO) {
        log.info("✅ Received ProductDTO: {}", productDTO);

        Product product = productMapper.mapToEntity(productDTO);
        productRepository.save(product);
    }

//    @KafkaListener(topics = "product-topic", groupId = "product-group")
//    public void consumeProduct(String message) {
//        log.info("Received message: {}", message);
//
//        // Option 1: If message is JSON and you want to deserialize:
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            ProductDTO productDTO = objectMapper.readValue(message, ProductDTO.class);
//            Product product = productMapper.mapToEntity(productDTO);
//            productRepository.save(product);
//        } catch (Exception e) {
//            log.error("Failed to parse product message", e);
//        }
//    }
}