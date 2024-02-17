package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.PlaceDto;
import com.hukathon.openspace.entity.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PlaceMapper implements Function<Place, PlaceDto> {
    private final ImageMapper imageMapper;
    private final SocialMediaMapper socialMediaMapper;

    @Override
    public PlaceDto apply(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getName(),
                place.getDescription(),
                place.getAddress(),
                imageMapper.mapToDtoList(place.getImages()),
                socialMediaMapper.mapToDtoList(place.getContacts())
        );
    }
}
