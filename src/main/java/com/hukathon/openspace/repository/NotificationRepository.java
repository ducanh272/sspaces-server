package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("SELECT distinct n FROM Notification n join n.notificationStatuses r where r.user.id = ?1")
    List<Notification> findNotificationByUserId(Integer userId);

    @Query(value = "SELECT nextval('notification_notification_id_seq')", nativeQuery = true)
    Integer getNextNotificationId();
}
