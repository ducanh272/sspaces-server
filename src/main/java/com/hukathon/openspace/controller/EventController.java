package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.EventDto;
import com.hukathon.openspace.dto.PlaceDto;
import com.hukathon.openspace.service.EventService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @GetMapping("")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDto> getPlaceById(@PathVariable Integer eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

}
