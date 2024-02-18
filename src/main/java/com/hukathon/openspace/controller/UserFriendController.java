package com.hukathon.openspace.controller;

import com.hukathon.openspace.dto.UserEmail;
import com.hukathon.openspace.dto.UserFriendRequest;
import com.hukathon.openspace.dto.UserFriendResponse;
import com.hukathon.openspace.entity.User;
import com.hukathon.openspace.service.CustomUserDetailsService;
import com.hukathon.openspace.service.UserFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserFriendController {
    private final UserFriendService userFriendService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(
            @RequestBody UserEmail userEmail
    ) {
        return ResponseEntity.ok(customUserDetailsService.findByEmail(userEmail.getEmail()));
    }

    @PostMapping("/requestFriend")
    public ResponseEntity<UserFriendResponse> makeFriendRequest(
            @RequestBody UserFriendRequest friendRequest
    ) {
        return ResponseEntity.ok(userFriendService.makeFriendRequest(friendRequest));
    }


    @GetMapping("/{userId}/pendingRequest")
    public ResponseEntity<List<UserFriendResponse>> getPendingRequest(
            @PathVariable Integer userId
    ) {
        System.out.println(userId);
        return ResponseEntity.ok(userFriendService.getPendingRequest(userId));
    }

    @PostMapping("{userId}/pendingRequest")
    public ResponseEntity<UserFriendResponse> approveRequest(
            @PathVariable Integer userId,
            @RequestParam Integer userFriendId
    ) {
        return ResponseEntity.ok(userFriendService.approveRequest(userId, userFriendId));
    }

    @PostMapping("{userId}/deleteRequest")
    public void rejectRequest(
            @PathVariable Integer userId,
            @RequestParam Integer userFriendId
    ) {
        userFriendService.rejectRequest(userId, userFriendId);
    }

    @GetMapping("/{userId}/friendList")
    public ResponseEntity<List<UserFriendResponse>> getFriendList(
            @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(userFriendService.getFriendList(userId));
    }

}
