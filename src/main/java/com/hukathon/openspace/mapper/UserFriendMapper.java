package com.hukathon.openspace.mapper;

import com.hukathon.openspace.dto.UserFriendResponse;
import com.hukathon.openspace.entity.UserFriend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserFriendMapper implements Function<UserFriend, UserFriendResponse> {
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserFriendResponse apply(UserFriend userFriend) {
        return new UserFriendResponse(
                userFriend.getId(),
                userDtoMapper.apply(userFriend.getUser_id1()),
                userDtoMapper.apply(userFriend.getUser_id2()),
                userFriend.getFriendStatus(),
                DateUtils.toDate(userFriend.getCreatedAt()),
                DateUtils.toDate(userFriend.getUpdatedAt())
        );
    }
}
