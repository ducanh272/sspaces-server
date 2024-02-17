package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.entity.EventImage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventImageMapper {

    public List<EventImageDto> mapToDtoList(List<EventImage> eventImages) {
        return eventImages.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EventImageDto mapToDto(EventImage eventImage) {
        return new EventImageDto(
                eventImage.getId(),
                eventImage.getImageLink()
        );
    }
}
