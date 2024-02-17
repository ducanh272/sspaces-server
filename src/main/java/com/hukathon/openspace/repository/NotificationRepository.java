package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
//    List<Notification> findNotificationByUserId(Integer userId);
}