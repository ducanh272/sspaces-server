package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFriendRepository  extends JpaRepository<UserFriend, Integer> {
    @Query("SELECT uf FROM UserFriend uf " +
            "WHERE (uf.user_id1.id = ?1 AND uf.friendStatus = 'REQUEST_USER2') " +
            "OR (uf.user_id2.id = ?1 AND uf.friendStatus = 'REQUEST_USER1')")
    List<UserFriend> getPendingRequest(Integer userId);

    @Query("SELECT uf FROM UserFriend uf " +
            "WHERE (uf.user_id1.id = ?1 AND uf.friendStatus = 'REQUEST_USER2' AND uf.id = ?2) " +
            "OR (uf.user_id2.id = ?1 AND uf.friendStatus = 'REQUEST_USER1' AND uf.id = ?2)")
    UserFriend getAReQuest(Integer userId, Integer userFriendId);

    @Query("SELECT uf FROM UserFriend uf " +
            "WHERE (uf.user_id1.id = ?1 AND uf.friendStatus = 'FRIEND') " +
            "OR (uf.user_id2.id = ?1 AND uf.friendStatus = 'FRIEND')")
    List<UserFriend> getFriendList(Integer userId);
}
