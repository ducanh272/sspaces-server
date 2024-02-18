package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.NotificationIdDto;
import com.hukathon.openspace.dto.NotificationResponse;
import com.hukathon.openspace.entity.Notification;
import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.mapper.NotificationMapper;
import com.hukathon.openspace.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @GetMapping("/{userId}/notifications")
    public List<NotificationResponse> getAllNotifications(
            @PathVariable Integer userId
    ) {
        return notificationRepository.findNotificationByUserId(userId)
                .stream()
                .map(notificationMapper)
                .collect(Collectors.toList());
    }

    @PostMapping("/{userId}/notifications")
    public NotificationResponse updateNotification(
            @RequestBody NotificationIdDto notificationIdDto
            ) {
        var notification = notificationRepository.findById(notificationIdDto.getNotificationId())
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        notification.setRead(!notification.isRead());
        notificationRepository.save(notification);

        return notificationMapper.apply(notification);
    }
}
