package com.services.clients.notification;

import com.services.clients.notification.dto.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notification", path = "api/v1/notification")
public interface NotificationClient {
    @PostMapping("/")
    void sendNotification(NotificationRequest notificationRequest);
}