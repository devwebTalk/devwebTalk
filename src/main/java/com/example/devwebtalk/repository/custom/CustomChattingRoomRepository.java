package com.example.devwebtalk.repository.custom;

import com.example.devwebtalk.entity.ChattingRoom;
import com.example.devwebtalk.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Kim Young Long.
 * Date : 2021-09-17.
 * Github : https://github.com/kha0213
 * Blog : https://kha0213.github.io/
 * instagram : https://www.instagram.com/moon_maria__/
 */
public interface CustomChattingRoomRepository {
    public Optional<ChattingRoom> findOneByUserAndFriend(List<User> users);
}
