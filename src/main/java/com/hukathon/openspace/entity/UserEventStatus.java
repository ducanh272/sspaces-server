package com.hukathon.openspace.entity;

import com.hukathon.openspace.enums.FriendStatus;
import com.hukathon.openspace.enums.UserToEventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_event_status")
public class UserEventStatus {
    @EmbeddedId
    private UserEventKey userEventKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User userEvent;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserToEventStatus status;
}
