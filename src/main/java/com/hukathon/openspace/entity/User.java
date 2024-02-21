package com.hukathon.openspace.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private String address;

    private String gender;

    @ManyToMany
    @JoinTable(
            name = "place_following",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "place_id"))
    private List<Place> likedPlaces;

    @ManyToMany
    @JoinTable(
            name = "chat_member",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "box_chat_id"))
    private List<BoxChat> joinedChats;

    @OneToMany(mappedBy = "user")
    private List<NotificationStatus> notificationStatuses;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Message> userMessages;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Notification> senderNotification;
}

