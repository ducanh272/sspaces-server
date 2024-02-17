package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.EventTagDto;
import com.hukathon.openspace.entity.EventImage;
import com.hukathon.openspace.entity.EventTag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventTagMapper {

    public List<EventTagDto> mapToDtoList(List<EventTag> eventTags) {
        return eventTags.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EventTagDto mapToDto(EventTag eventTag) {
        return new EventTagDto(
                eventTag.getId(),
                eventTag.getNameTag()
        );
    }
}
