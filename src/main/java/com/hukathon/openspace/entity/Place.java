package com.hukathon.openspace.entity;

import com.hukathon.openspace.entity.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "place")
public class Place {

    @Id
    @Column(name = "place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "place_name")
    private String name;

    @Column(name = "place_description", columnDefinition = "text")
    private String description;

    @Column(name = "place_address", columnDefinition = "varchar(255)")
    private String address;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<SocialMedia> contacts;

    @ManyToMany(mappedBy = "likedPlaces")
    private List<User> followers;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Notification> placeNotifications;

}
