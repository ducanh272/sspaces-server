package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.UserDto;
import com.hukathon.openspace.entity.User;
import com.hukathon.openspace.mapper.UserDtoMapper;
import com.hukathon.openspace.repository.BoxChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxChatService {
    private final BoxChatRepository boxChatRepository;
    private final UserDtoMapper userDtoMapper;

    public List<UserDto> getAllChatMembers(Integer boxChatId) {
        return boxChatRepository.findById(boxChatId).get().getMembers()
                .stream()
                .map(userDtoMapper)
                .collect(Collectors.toList());
    }


}
