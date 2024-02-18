package com.hukathon.openspace.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hukathon.openspace.dto.UserDto;
import com.hukathon.openspace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
    private UserDto userDto;
}
