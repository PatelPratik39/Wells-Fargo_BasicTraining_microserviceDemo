package com.wf.userservice.controller;

import com.wf.userservice.kafka.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaTestController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaTestController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam String message) {
        kafkaProducerService.sendMessage(message);
        return ResponseEntity.ok("Message sent to Kafka");
    }
}