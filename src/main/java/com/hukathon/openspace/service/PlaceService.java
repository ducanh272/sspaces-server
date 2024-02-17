package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.ImageDto;
import com.hukathon.openspace.dto.PlaceDto;
import com.hukathon.openspace.dto.SocialMediaDto;

import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<PlaceDto> getAllPlaces() {
        return placeRepository.findAll()
                .stream()
                .map(place -> new PlaceDto(
                        place.getId(),
                        place.getName(),
                        place.getDescription(),
                        place.getAddress(),
                        place.getImages()
                                .stream()
                                .map(image -> new ImageDto(
                                        image.getId(),
                                        image.getImageLink()
                                )).collect(Collectors.toList()),
                        place.getContacts()
                                .stream()
                                .map(socialMedia -> new SocialMediaDto(
                                        socialMedia.getId(),
                                        socialMedia.getContactLink()
                                )).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public PlaceDto getPlaceById(Integer placeId) {
        return placeRepository.findById(placeId)
                .map(place -> new PlaceDto(
                        place.getId(),
                        place.getName(),
                        place.getDescription(),
                        place.getAddress(),
                        place.getImages()
                                .stream()
                                .map(image -> new ImageDto(
                                        image.getId(),
                                        image.getImageLink()
                                )).collect(Collectors.toList()),
                        place.getContacts()
                                .stream()
                                .map(socialMedia -> new SocialMediaDto(
                                        socialMedia.getId(),
                                        socialMedia.getContactLink()
                                )).collect(Collectors.toList())
                ))
                .orElseThrow(() -> new NotFoundException("Place not found"));
    }

}
