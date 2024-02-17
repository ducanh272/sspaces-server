package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.EventDto;
import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.EventTagDto;
import com.hukathon.openspace.dto.RegistrationLinkDto;
import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.mapper.EventMapper;
import com.hukathon.openspace.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(eventMapper)
                .collect(Collectors.toList());
    }

    public EventDto getEventById(Integer eventId) {
        return eventRepository.findById(eventId)
                .map(eventMapper)
                .orElseThrow(() -> new NotFoundException("Event not found"));
    }

}
