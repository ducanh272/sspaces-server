package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.NotificationStatus;
import com.hukathon.openspace.entity.NotificationStatusKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationStatusRepository extends JpaRepository<NotificationStatus, NotificationStatusKey> {
}
