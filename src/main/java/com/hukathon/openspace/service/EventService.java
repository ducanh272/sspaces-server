package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.EventDto;
import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.EventTagDto;
import com.hukathon.openspace.dto.RegistrationLinkDto;
import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDto> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(event -> new EventDto(
                        event.getId(),
                        event.getName(),
                        event.getDescription(),
                        event.getTime(),
                        event.getImages()
                                .stream()
                                .map(eventImage -> new EventImageDto(
                                        eventImage.getId(),
                                        eventImage.getImageLink()
                                )).collect(Collectors.toList()),
                        event.getTags()
                                .stream()
                                .map(eventTag -> new EventTagDto(
                                        eventTag.getId(),
                                        eventTag.getNameTag()
                                )).collect(Collectors.toList()),
                        event.getLinks()
                                .stream()
                                .map(eventLink -> new RegistrationLinkDto(
                                        eventLink.getId(),
                                        eventLink.getLink()
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public EventDto getEventById(Integer eventId) {
        return eventRepository.findById(eventId)
                .map(event -> new EventDto(
                        event.getId(),
                        event.getName(),
                        event.getDescription(),
                        event.getTime(),
                        event.getImages()
                                .stream()
                                .map(eventImage -> new EventImageDto(
                                        eventImage.getId(),
                                        eventImage.getImageLink()
                                )).collect(Collectors.toList()),
                        event.getTags()
                                .stream()
                                .map(eventTag -> new EventTagDto(
                                        eventTag.getId(),
                                        eventTag.getNameTag()
                                )).collect(Collectors.toList()),
                        event.getLinks()
                                .stream()
                                .map(eventLink -> new RegistrationLinkDto(
                                        eventLink.getId(),
                                        eventLink.getLink()
                                )).collect(Collectors.toList())
                ))
                .orElseThrow(() -> new NotFoundException("Event not found"));
    }

}
