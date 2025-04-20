package com.wf.notificationservice.mapper;

import com.wf.notificationservice.dto.NotificationRequest;
import com.wf.notificationservice.entity.Notification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class NotificationMapper {
    // This class can be used to map between Notification entity and DTOs if needed
    // Currently, it is empty as the Notification entity is directly used in the repository
    // You can add methods here to convert between Notification and other objects if needed

    public static Notification mapToEntity(NotificationRequest request) {
        return Notification.builder()
                .userId(request.getUserId())
                .message(request.getMessage())
                .source(request.getSource())
                .payload(request.getPayload())
                .timestamp(LocalDateTime.now())
                .build();
    }
}