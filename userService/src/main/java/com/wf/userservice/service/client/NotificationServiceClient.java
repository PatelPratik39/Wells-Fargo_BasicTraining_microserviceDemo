package com.wf.userservice.service.client;

import com.wf.userservice.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION-SERVICE")
public interface NotificationServiceClient {
    @PostMapping("/api/notifications")
    Void sendNotification(@RequestBody NotificationDTO notificationDTO);
}