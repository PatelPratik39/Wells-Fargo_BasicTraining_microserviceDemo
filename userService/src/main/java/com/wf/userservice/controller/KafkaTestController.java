package com.wf.userservice.controller;


import com.wf.userservice.kafka.UserKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kafka")
public class KafkaTestController {

    private final UserKafkaProducer kafkaProducer;

    @PostMapping("/notify-order")
    public ResponseEntity<String> notifyOrder(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Event sent to Order Service");
    }
}