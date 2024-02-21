package com.hukathon.openspace.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_name")
    private String name;

    @Column(name = "event_description", columnDefinition = "text")
    private String description;

    @Column(name = "event_time", columnDefinition = "timestamp")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="place_id")
    private Place place;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<RegistrationLink> links;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> images;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventTag> tags;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Notification> eventNotification;

    @OneToOne(mappedBy = "event")
    private BoxChat boxChat;

    @OneToMany(mappedBy = "event")
    private List<UserEventStatus> userEventStatuses;

}

