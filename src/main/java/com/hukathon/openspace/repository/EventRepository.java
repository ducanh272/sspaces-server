package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
