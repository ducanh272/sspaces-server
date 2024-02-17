package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.EventImageDto;
import com.hukathon.openspace.dto.ImageDto;
import com.hukathon.openspace.entity.EventImage;
import com.hukathon.openspace.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageMapper {

    public List<ImageDto> mapToDtoList(List<Image> images) {
        return images.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ImageDto mapToDto(Image image) {
        return new ImageDto(
                image.getId(),
                image.getImageLink()
        );
    }

}
