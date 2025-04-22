package com.wf.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class NotificationRequest {

    private String userId;
    private String message;
    private String source;
    private Map<String, Object> payload;
}