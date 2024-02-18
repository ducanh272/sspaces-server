package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("SELECT n FROM Notification n join n.sender")
    List<Notification> findNotificationByUserId(Integer userId);
}
