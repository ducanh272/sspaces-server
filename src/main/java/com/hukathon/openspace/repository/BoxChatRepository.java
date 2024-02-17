package com.hukathon.openspace.repository;

import com.hukathon.openspace.entity.BoxChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxChatRepository extends JpaRepository<BoxChat, Integer> {
}
