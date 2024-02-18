package com.hukathon.openspace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Notification {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean isRead;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToMany(mappedBy = "notifications")
    private List<User> receivers;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

}
