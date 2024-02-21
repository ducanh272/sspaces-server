package com.hukathon.openspace.service;

import com.hukathon.openspace.dto.UserDto;
import com.hukathon.openspace.dto.UserFriendId;
import com.hukathon.openspace.dto.UserFriendRequest;
import com.hukathon.openspace.dto.UserFriendResponse;
import com.hukathon.openspace.entity.User;
import com.hukathon.openspace.entity.UserFriend;
import com.hukathon.openspace.enums.FriendStatus;
import com.hukathon.openspace.exception.NotFoundException;
import com.hukathon.openspace.mapper.UserDtoMapper;
import com.hukathon.openspace.mapper.UserFriendMapper;
import com.hukathon.openspace.repository.UserFriendRepository;
import com.hukathon.openspace.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFriendService {
    private final UserFriendRepository userFriendRepository;
    private final UserRepository userRepository;
    private final UserFriendMapper userFriendMapper;


    @Transactional
    public UserFriendResponse makeFriendRequest(UserFriendRequest friendRequest) {

        User user1 = userRepository.findById(friendRequest.getUser_id1())
                .orElseThrow(() -> new NotFoundException("User 1 not found"));

        User user2 = userRepository.findById(friendRequest.getUser_id2())
                .orElseThrow(() -> new NotFoundException("User 2 not found"));

        var userFriend = new UserFriend();
        if (friendRequest.getUser_id1() < friendRequest.getUser_id2()) {
             userFriend = UserFriend.builder()
                    .user_id1(user1)
                    .user_id2(user2)
                    .friendStatus(FriendStatus.REQUEST_USER1)
                    .build();
        } else if (friendRequest.getUser_id1() > friendRequest.getUser_id2()) {
            userFriend = UserFriend.builder()
                    .user_id1(user2)
                    .user_id2(user1)
                    .friendStatus(FriendStatus.REQUEST_USER2)
                    .build();
        }
        userFriendRepository.save(userFriend);
        return userFriendMapper.apply(userFriend);
    }

    public List<UserFriendResponse> getPendingRequest(Integer userId) {
        List<UserFriend> pendingRequests = userFriendRepository.getPendingRequest(userId);
        return pendingRequests
                .stream()
                .map(userFriendMapper)
                .collect(Collectors.toList());
    }

    public UserFriendResponse approveRequest(Integer userId, UserFriendId userFriendId) {
        UserFriend friendRequest = userFriendRepository.getAReQuest(userId, userFriendId.getUserFriendId());
        friendRequest.setFriendStatus(FriendStatus.FRIEND);
        userFriendRepository.save(friendRequest);

        return userFriendMapper.apply(friendRequest);
    }

    public void rejectRequest(Integer userId, Integer userFriendId) {
        UserFriend friendRequest = userFriendRepository.getAReQuest(userId, userFriendId);
        userFriendRepository.delete(friendRequest);
    }

    public List<User> getFriendList(Integer userId) {
        List<User> list1 = userFriendRepository.getFriendList1(userId);
        List<User> list2 = userFriendRepository.getFriendList2(userId);
        List<User> allFriend = new ArrayList<>();
        allFriend.addAll(list1);
        allFriend.addAll(list2);

        return allFriend;

    }
}
