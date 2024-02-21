package com.hukathon.openspace.entity;

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
@Table(name = "user_place_rating")
public class UserPlaceRating {
    @EmbeddedId
    private UserPlaceKey userPlaceKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User userPlace;

    @ManyToOne
    @MapsId("placeId")
    @JoinColumn(name = "place_id")
    private Place place;
    
    @Column(name = "rating")
    private Integer rating;
}
