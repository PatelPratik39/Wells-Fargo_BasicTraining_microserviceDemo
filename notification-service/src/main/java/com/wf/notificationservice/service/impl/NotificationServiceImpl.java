package com.wf.notificationservice.service.impl;

import com.wf.notificationservice.dto.NotificationRequest;
import com.wf.notificationservice.entity.Notification;
import com.wf.notificationservice.mapper.NotificationMapper;
import com.wf.notificationservice.repository.NotificationRepository;
import com.wf.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void processNotification(NotificationRequest request) {
        Notification savedNotification = NotificationMapper.mapToEntity(request);
        notificationRepository.save(savedNotification);
        log.info("✅ Notification saved successfully for user {}", request.getUserId());
    }

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }



}