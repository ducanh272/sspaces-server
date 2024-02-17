package com.hukathon.openspace.entity;

import com.hukathon.openspace.enums.FriendStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_friend")
public class UserFriend {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id1", referencedColumnName = "user_id")
    private User user_id1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id2", referencedColumnName = "user_id")
    private User user_id2;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FriendStatus friendStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
