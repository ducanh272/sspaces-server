package com.hukathon.openspace.mapper;


import com.hukathon.openspace.dto.NotificationResponse;
import com.hukathon.openspace.dto.NotificationStatusDto;
import com.hukathon.openspace.entity.Notification;
import com.hukathon.openspace.entity.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class NotiStatusMapper implements Function<NotificationStatus, NotificationStatusDto> {
    private final EventMapper eventMapper;
    private final UserDtoMapper userDtoMapper;


    @Override
    public NotificationStatusDto apply(NotificationStatus notificationStatus) {
        return new NotificationStatusDto(
                notificationStatus.getUser().getId(),
                notificationStatus.getNotification().getId(),
                notificationStatus.isRead()
        );
    }
}
