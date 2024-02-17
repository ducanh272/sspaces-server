package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.RegistrationLinkDto;
import com.hukathon.openspace.entity.EventImage;
import com.hukathon.openspace.entity.RegistrationLink;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RegistrationLinkMapper {

    public List<RegistrationLinkDto> mapToDtoList(List<RegistrationLink> registrationLinks) {
        return registrationLinks.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public RegistrationLinkDto mapToDto(RegistrationLink registrationLink) {
        return new RegistrationLinkDto(
                registrationLink.getId(),
                registrationLink.getLink()
        );
    }
}
