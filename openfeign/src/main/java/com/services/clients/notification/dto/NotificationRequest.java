package com.services.clients.notification.dto;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}
