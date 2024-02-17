package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.EventDto;
import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.UserDto;
import com.hukathon.openspace.entity.Event;
import com.hukathon.openspace.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class EventMapper implements Function<Event, EventDto> {

    private final EventImageMapper eventImageMapper;
    private final EventTagMapper eventTagMapper;
    private final RegistrationLinkMapper registrationLinkMapper;
    private final PlaceMapper placeMapper;

    @Override
    public EventDto apply(Event event) {
        return new EventDto(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getTime(),
                placeMapper.apply(event.getPlace()),
                eventImageMapper.mapToDtoList(event.getImages()),
                eventTagMapper.mapToDtoList(event.getTags()),
                registrationLinkMapper.mapToDtoList(event.getLinks())
        );
    }
}
