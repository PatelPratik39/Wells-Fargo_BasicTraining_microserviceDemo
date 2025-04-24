package com.wf.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {

    private String userId;
    private String message;
    private String source;
    private Map<String, Object> payload;
}