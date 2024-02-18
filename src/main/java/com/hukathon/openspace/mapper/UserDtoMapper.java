package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.UserDto;
import com.hukathon.openspace.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getAddress(),
                user.getGender()
        );
    }
}
