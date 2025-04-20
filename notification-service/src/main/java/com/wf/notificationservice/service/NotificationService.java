package com.wf.notificationservice.service;

import com.wf.notificationservice.dto.NotificationRequest;
import com.wf.notificationservice.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {

    void processNotification(NotificationRequest request);
    List<Notification> getNotificationsByUserId(String userId);
    List<Notification> getAllNotifications();

}