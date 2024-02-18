package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.NotificationResponse;
import com.hukathon.openspace.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class NotificationMapper implements Function<Notification, NotificationResponse> {
    private final EventMapper eventMapper;
    private final UserDtoMapper userDtoMapper;

    @Override
    public NotificationResponse apply(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getTitle(),
                notification.getContent(),
                notification.isRead(),
                eventMapper.apply(notification.getEvent()),
                userDtoMapper.apply(notification.getSender())
        );
    }
}
