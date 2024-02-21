package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.EventDto;
import com.hukathon.openspace.dto.NotiCreateRequest;
import com.hukathon.openspace.dto.NotificationResponse;
import com.hukathon.openspace.entity.Event;
import com.hukathon.openspace.entity.Notification;
import com.hukathon.openspace.entity.NotificationStatus;
import com.hukathon.openspace.entity.User;
import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.mapper.NotificationMapper;
import com.hukathon.openspace.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final UserFriendService userFriendService;
    private final NotificationStatusRepository notificationStatusRepository;

    public NotificationResponse sendNotificationToFriends(NotiCreateRequest notiCreateRequest) {
        User sender = userRepository.findById(notiCreateRequest.getSenderId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Event event = eventRepository.findById(notiCreateRequest.getEventId())
                .orElseThrow(() -> new NotFoundException("Event not found"));

        List<User> receivers = userFriendService.getFriendList(notiCreateRequest.getSenderId());

//        List<NotificationStatus> notificationStatusList = new ArrayList<>();
////        Integer lastestNotiId = notificationRepository.getNextNotificationId() - 1;
////
////        for (User receiver : receivers) {
////            var notificationStatus = NotificationStatus.builder()
////                    .user(receiver)
////                    .isRead(false)
////                    .build();
////            notificationStatusList.add(notificationStatus);
////        }

        var notification = Notification.builder()
                .title(event.getName())
                .content(event.getName() + " sắp bắt đầu")
                .event(event)
                .sender(sender)
                .build();
        notificationRepository.save(notification);

        return notificationMapper.apply(notification);
    }

}

