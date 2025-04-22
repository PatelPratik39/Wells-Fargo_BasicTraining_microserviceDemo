package com.wf.notificationservice.kafka;

import com.wf.notificationservice.dto.NotificationRequest;
import com.wf.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private NotificationService notificationService;

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(NotificationRequest notificationRequest) {
        notificationService.processNotification(notificationRequest);
    }
}