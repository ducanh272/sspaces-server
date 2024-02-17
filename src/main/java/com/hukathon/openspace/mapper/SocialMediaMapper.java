package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.SocialMediaDto;
import com.hukathon.openspace.entity.EventImage;
import com.hukathon.openspace.entity.SocialMedia;

import java.util.List;
import java.util.stream.Collectors;

public class SocialMediaMapper {

    public List<SocialMediaDto> mapToDtoList(List<SocialMedia> socialMediaList) {
        return socialMediaList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public SocialMediaDto mapToDto(SocialMedia socialMedia) {
        return new SocialMediaDto(
                socialMedia.getId(),
                socialMedia.getContactLink()
        );
    }
}
