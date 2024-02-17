package com.hukathon.openspace.entity;

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
@Table(name = "box_chat")
public class BoxChat {

    @Id
    @Column(name = "box_chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "boxchat", cascade = CascadeType.ALL)
    private List<Message> messageList;

    @ManyToMany(mappedBy = "joinedChats")
    private List<User> members;
}
