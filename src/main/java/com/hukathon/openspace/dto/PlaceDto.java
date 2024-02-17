package com.hukathon.openspace.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hukathon.openspace.entity.Image;
import com.hukathon.openspace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaceDto {

    private Integer id;

    private String name;

    private String description;

    private String address;

    private List<ImageDto> images;

    private List<SocialMediaDto> contacts;

//    private List<User> followers;
}
