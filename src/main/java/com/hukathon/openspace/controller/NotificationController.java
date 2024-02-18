package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.NotificationResponse;
import com.hukathon.openspace.entity.Notification;
import com.hukathon.openspace.mapper.NotificationMapper;
import com.hukathon.openspace.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @GetMapping("/users/{userId}/notifications")
    public List<NotificationResponse> getAllNotifications(
            @PathVariable Integer userId
    ) {
        return notificationRepository.findNotificationByUserId(userId)
                .stream()
                .map(notificationMapper)
                .collect(Collectors.toList());
    }
}
