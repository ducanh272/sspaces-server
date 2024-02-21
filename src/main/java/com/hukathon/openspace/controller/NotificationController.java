package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.*;
import com.hukathon.openspace.entity.Event;
import com.hukathon.openspace.entity.Notification;
import com.hukathon.openspace.entity.NotificationStatusKey;
import com.hukathon.openspace.entity.User;
import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.mapper.NotiStatusMapper;
import com.hukathon.openspace.mapper.NotificationMapper;
import com.hukathon.openspace.repository.EventRepository;
import com.hukathon.openspace.repository.NotificationRepository;
import com.hukathon.openspace.repository.NotificationStatusRepository;
import com.hukathon.openspace.repository.UserRepository;
import com.hukathon.openspace.service.NotificationService;
import com.hukathon.openspace.service.UserFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final NotificationStatusRepository notificationStatusRepository;
    private final NotificationMapper notificationMapper;
    private final NotiStatusMapper notiStatusMapper;
    private final NotificationService notificationService;
    private final UserFriendService userFriendService;
    private final Sinks.Many<List<Integer>> sink = Sinks.many().multicast().directBestEffort();

    @GetMapping("/users/{userId}/notifications")
    public List<NotificationResponse> getAllNotifications(
            @PathVariable Integer userId
    ) {
        return notificationRepository.findNotificationByUserId(userId)
                .stream()
                .map(notificationMapper)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/{userId}/updateNotification")
    public NotificationStatusDto updateNotification(
            @RequestBody NotificationIdDto notificationIdDto
            ) {
        var notificationStatus = notificationStatusRepository
                .findById(new NotificationStatusKey(
                        notificationIdDto.getUserId(),
                        notificationIdDto.getNotificationId()))
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        notificationStatus.setRead(!notificationStatus.isRead());
        notificationStatusRepository.save(notificationStatus);

        return notiStatusMapper.apply(notificationStatus);
    }


    @PostMapping("/sharenotifications")
    public ResponseEntity<NotificationResponse> sendNotificationToFriends(
            @RequestBody NotiCreateRequest notiCreateRequest) {
        List<Integer> allUserIds = userFriendService.getFriendList(notiCreateRequest.getSenderId())
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());

        sink.tryEmitNext(allUserIds);
        return ResponseEntity.ok(notificationService.sendNotificationToFriends(notiCreateRequest));
    }

    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:3000")
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Flux - " + LocalTime.now().toString());
    }

    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        List<Integer> allUserIds = userFriendService.getFriendList(5)
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());
//        sink.tryEmitNext(allUserIds);
        return  sink.asFlux()
                .map(userId -> {
                    String json = "{\"userId\": " + userId + "}";
                    return ServerSentEvent.<String>builder()
                            .event("custom-event")
                            .data(json)
                            .build();
                });
    }

}
