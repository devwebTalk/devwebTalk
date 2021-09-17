package com.example.devwebtalk.repository;

import com.example.devwebtalk.entity.ChattingRoom;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.custom.CustomChattingRoomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long>, CustomChattingRoomRepository {
    Optional<ChattingRoom> findOneByUserAndFriend(@Param("users") List<User> users);
}
